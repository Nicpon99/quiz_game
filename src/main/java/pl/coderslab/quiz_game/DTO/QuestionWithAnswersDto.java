package pl.coderslab.quiz_game.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class QuestionWithAnswersDto {
    private Long id;
    private String question;
    private List<AnswerDto> answers;
}
