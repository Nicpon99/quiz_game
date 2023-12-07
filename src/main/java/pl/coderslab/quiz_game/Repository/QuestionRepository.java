package pl.coderslab.quiz_game.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.quiz_game.entity.Question;

@Repository
public interface QuestionRepository  extends JpaRepository<Question, Long> {
}
