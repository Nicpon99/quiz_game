package pl.coderslab.quiz_game.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.quiz_game.DTO.AnswerRequestDto;
import pl.coderslab.quiz_game.Service.QuizApiService;

@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuizGameController {

    private final QuizApiService quizApiService;

    @GetMapping("/question")
    public String getQuestion() throws JsonProcessingException {
        return quizApiService.getQuestionWIthAnswers();
    }

    @PostMapping("/answer")
    public ResponseEntity getAnswer(@RequestBody AnswerRequestDto answerRequestDto){
        return quizApiService.getResult(answerRequestDto);
    }
}
