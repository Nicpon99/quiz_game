package pl.coderslab.quiz_game.Service;

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
import pl.coderslab.quiz_game.entity.Answer;
import pl.coderslab.quiz_game.entity.Question;

import java.util.Map;

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
}
