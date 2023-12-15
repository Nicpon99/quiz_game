package pl.coderslab.quiz_game.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.quiz_game.API.QuizApiQuestionDto;
import pl.coderslab.quiz_game.API.QuizApiResponse;
import pl.coderslab.quiz_game.DTO.AnswerDto;
import pl.coderslab.quiz_game.DTO.AnswerRequestDto;
import pl.coderslab.quiz_game.DTO.AnswerResponseDto;
import pl.coderslab.quiz_game.DTO.QuestionWithAnswersDto;
import pl.coderslab.quiz_game.entity.Answer;
import pl.coderslab.quiz_game.entity.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizApiService {

    @Value("${quiz.api.url}")
    private String apiUrl;

    @Value("${quiz.api.key}")
    private String apiKey;

    @Value("${quiz.api.limit}")
    private String limit;

    private final RestTemplate restTemplate;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Transactional
    public void fetchAndSaveQuestions(){
        String apiUrlWithParams = apiUrl + "?apiKey=" + apiKey + "&limit=" + limit;

        ResponseEntity<QuizApiQuestionDto[]> responseEntity = restTemplate.getForEntity(
                apiUrlWithParams,
                QuizApiQuestionDto[].class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            QuizApiQuestionDto[] quizApiQuestionDtos = responseEntity.getBody();

            if (quizApiQuestionDtos != null && quizApiQuestionDtos.length > 0) {
                for (QuizApiQuestionDto quizApiQuestionDto : quizApiQuestionDtos) {

                    Question question = Question.builder()
                            .api_id(quizApiQuestionDto.getId())
                            .question(quizApiQuestionDto.getQuestion())
                            .build();

                    questionService.saveQuestion(question);

                    for (Map.Entry<String, String> entry : quizApiQuestionDto.getAnswers().entrySet()) {
                        Answer answer = Answer.builder()
                                .answer(entry.getValue())
                                .correct(Boolean.valueOf(quizApiQuestionDto.getCorrect_answers()
                                        .get(entry.getKey() + "_correct")))
                                .question(question)
                                .build();

                        answerService.saveAnswer(answer);
                    }
                }
            }
        }
    }

    public String getQuestionWIthAnswers() throws JsonProcessingException {
        Question question = questionService.getRandomQuestion();
        List<Answer> answers = answerService.findAnswersByQuestion(question);
        List<AnswerDto> answerDtoList = new ArrayList<>();

        for (Answer answer : answers) {
            if (answer.getAnswer() != null) {
                AnswerDto answerDto = AnswerDto.builder()
                        .id(answer.getId())
                        .answer(answer.getAnswer())
                        .build();

                answerDtoList.add(answerDto);
            }
        }

        QuestionWithAnswersDto questionWithAnswersDto = QuestionWithAnswersDto
                .builder()
                .id(question.getId())
                .question(question.getQuestion())
                .answers(answerDtoList)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(questionWithAnswersDto);
    }

    public boolean checkAnswer(Long questionId, List<Long> chosenAnswer){
        Question question = questionService.findById(questionId);
        List<Answer> correctAnswers = answerService. findCorrectAnswerByQuestion(question);

        return correctAnswers.stream()
                .map(Answer::getId)
                .collect(Collectors.toSet())
                .equals(new HashSet<>(chosenAnswer));
    }

    public ResponseEntity<AnswerResponseDto> getResult(AnswerRequestDto answerRequestDto){
        Long questionId = answerRequestDto.getQuestionId();
        List<Long> chosenAnswers = answerRequestDto.getAnswers();

        boolean isCorrect = checkAnswer(questionId, chosenAnswers);

        AnswerResponseDto responseDto = new AnswerResponseDto(isCorrect);

        return ResponseEntity.ok(responseDto);
    }
}
