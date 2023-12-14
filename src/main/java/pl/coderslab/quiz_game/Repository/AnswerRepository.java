package pl.coderslab.quiz_game.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.quiz_game.entity.Answer;
import pl.coderslab.quiz_game.entity.Question;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAnswersByQuestion(Question question);
}
