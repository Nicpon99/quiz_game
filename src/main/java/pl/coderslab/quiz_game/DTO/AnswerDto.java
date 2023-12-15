package pl.coderslab.quiz_game.DTO;

import lombok.*;

@Data
@Builder
public class AnswerDto {
    private Long id;
    private String answer;
}
