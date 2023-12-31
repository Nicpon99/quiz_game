package pl.coderslab.quiz_game.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.quiz_game.entity.Question;

import java.util.Optional;

@Repository
public interface QuestionRepository  extends JpaRepository<Question, Long> {

    @Query("SELECT COUNT(q) FROM Question q")
    Optional<Long> getCountOfQuestions();

}
