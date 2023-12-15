package pl.coderslab.quiz_game.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.quiz_game.Repository.AnswerRepository;
import pl.coderslab.quiz_game.entity.Answer;
import pl.coderslab.quiz_game.entity.Question;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void saveAnswer(Answer answer){
        answerRepository.save(answer);
    }

    public List<Answer> findAnswersByQuestion(Question question){
        return answerRepository.findAnswersByQuestion(question);
    }

    public List<Answer> findCorrectAnswerByQuestion(Question question){
        return answerRepository.findCorrectAnswersByQuestion(question);
    }
}
