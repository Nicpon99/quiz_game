package pl.coderslab.quiz_game.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.quiz_game.Repository.QuestionRepository;
import pl.coderslab.quiz_game.entity.Question;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }


    public Question getRandomQuestion() {
        Long countOfQuestions = questionRepository.getCountOfQuestions().orElse(0L);

        if (countOfQuestions == 0) {
            throw new NoSuchElementException("No questions in the database!");
        } else {

            Random random = new Random();

            Long randomQuestionId = random.nextLong(countOfQuestions) + 1;

            return findById(randomQuestionId);

            // W przypadku, gdy nie moglibyśmy polegać na założeniu, w którym identyfikatory pytań nie są narastjące
            // oraz nie jest możliwe usuwanie danych możnaby losować pytanie w poniższy sposób:

//            int randomIndex = random.nextInt(countOfQuestions.intValue());
//
//            List<Question> questions = findAll();
//
//            return questions.get(randomIndex);
        }
    }
}
