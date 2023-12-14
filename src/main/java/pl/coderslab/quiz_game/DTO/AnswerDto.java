package pl.coderslab.quiz_game.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AnswerDto {
    private Long id;
    private String answer;
}
