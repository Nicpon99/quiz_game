package pl.coderslab.quiz_game.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.quiz_game.DTO.AnswerRequestDto;
import pl.coderslab.quiz_game.DTO.AnswerResponseDto;
import pl.coderslab.quiz_game.Service.QuestionService;
import pl.coderslab.quiz_game.Service.QuizApiService;
import pl.coderslab.quiz_game.entity.Question;


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
    public ResponseEntity<AnswerResponseDto> getAnswer(@RequestBody AnswerRequestDto answerRequestDto){
        return quizApiService.getResult(answerRequestDto);
    }
}
