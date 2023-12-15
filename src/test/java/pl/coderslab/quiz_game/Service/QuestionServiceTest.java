package pl.coderslab.quiz_game.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.coderslab.quiz_game.Repository.QuestionRepository;
import pl.coderslab.quiz_game.entity.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @Test
    public void testFindAll() {
        Question question1 = new Question();
        Question question2 = new Question();
        List<Question> questions = Arrays.asList(question1, question2);

        when(questionRepository.findAll()).thenReturn(questions);

        List<Question> result = questionService.findAll();

        assertThat(result).isEqualTo(questions);
    }

    @Test
    public void testGetRandomQuestionWithQuestions() {
        Question question = new Question();
        when(questionRepository.getCountOfQuestions()).thenReturn(Optional.of(1L));
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        Question result = questionService.getRandomQuestion();

        assertThat(result).isEqualTo(question);
    }

}