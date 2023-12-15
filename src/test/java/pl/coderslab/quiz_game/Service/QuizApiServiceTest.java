package pl.coderslab.quiz_game.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.coderslab.quiz_game.API.QuizApiQuestionDto;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class QuizApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private QuizApiService quizApiService;

    @Test
    public void testFetchAndSaveQuestions() {
        QuizApiQuestionDto quizApiQuestionDto = new QuizApiQuestionDto();
        quizApiQuestionDto.setId(1L);
        quizApiQuestionDto.setQuestion("Test question");

        quizApiQuestionDto.setAnswers(Collections.emptyMap());

        QuizApiQuestionDto[] quizApiQuestionDtos = {quizApiQuestionDto};
        when(restTemplate.getForEntity(anyString(), eq(QuizApiQuestionDto[].class)))
                .thenReturn(new ResponseEntity<>(quizApiQuestionDtos, HttpStatus.OK));

        quizApiService.fetchAndSaveQuestions();

        verify(questionService, times(1)).saveQuestion(any());

        verify(answerService, times(quizApiQuestionDto.getAnswers().size())).saveAnswer(any());
    }
}
