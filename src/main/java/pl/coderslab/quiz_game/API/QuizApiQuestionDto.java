package pl.coderslab.quiz_game.API;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class QuizApiQuestionDto {
    private Long id;
    private String question;
    private Map<String, String> answers;
    private Map<String, String> correct_answers;
}
