package pl.coderslab.quiz_game.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.coderslab.quiz_game.Repository.AnswerRepository;
import pl.coderslab.quiz_game.entity.Answer;
import pl.coderslab.quiz_game.entity.Question;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @InjectMocks
    private AnswerService answerService;

    @Test
    public void testFindAnswersByQuestion() {
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        List<Answer> answers = Arrays.asList(answer1, answer2);

        when(answerRepository.findAnswersByQuestion(question)).thenReturn(answers);

        List<Answer> result = answerService.findAnswersByQuestion(question);

        assertThat(result).isEqualTo(answers);
    }

}