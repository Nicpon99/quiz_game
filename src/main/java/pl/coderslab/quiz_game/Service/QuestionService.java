package pl.coderslab.quiz_game.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.quiz_game.Repository.QuestionRepository;
import pl.coderslab.quiz_game.entity.Question;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void saveQuestion(Question question){
        questionRepository.save(question);
    }

    public Question getRandomQuestion(){
        return null;
    }
}
