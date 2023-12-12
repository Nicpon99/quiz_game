package pl.coderslab.quiz_game.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.quiz_game.Repository.AnswerRepository;
import pl.coderslab.quiz_game.entity.Answer;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void saveAnswer(Answer answer){
        answerRepository.save(answer);
    }
}
