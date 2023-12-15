package pl.coderslab.quiz_game.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AnswerRequestDto {
    private Long questionId;
    private List<Long> answers;
}
