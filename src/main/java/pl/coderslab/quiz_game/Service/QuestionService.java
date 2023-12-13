package pl.coderslab.quiz_game.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.quiz_game.Repository.QuestionRepository;
import pl.coderslab.quiz_game.entity.Question;

import java.util.NoSuchElementException;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void saveQuestion(Question question){
        questionRepository.save(question);
    }

    public Question findById(Long id){
        return questionRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Not found question with this ID!"));
    }


    public Question getRandomQuestion(){
        Long countOfQuestions = questionRepository.getCountOfQuestions().orElseThrow(
                () -> new NoSuchElementException("No questions in the database!"));

            Random random = new Random();
            Long randomQuestionId = random.nextLong((countOfQuestions - 1) + 1) + 1;

            return findById(randomQuestionId);
    }
}
