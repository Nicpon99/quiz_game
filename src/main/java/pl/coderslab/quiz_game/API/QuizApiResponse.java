package pl.coderslab.quiz_game.API;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizApiResponse {
    private List<QuizApiQuestionDto> questions;
}
