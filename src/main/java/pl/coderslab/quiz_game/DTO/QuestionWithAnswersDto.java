package pl.coderslab.quiz_game.DTO;

import lombok.*;

import java.util.List;

@Data
@Builder
public class QuestionWithAnswersDto {
    private Long id;
    private String question;
    private List<AnswerDto> answers;
}
