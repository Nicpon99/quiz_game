package pl.coderslab.quiz_game.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.quiz_game.Service.QuestionService;
import pl.coderslab.quiz_game.Service.QuizApiService;
import pl.coderslab.quiz_game.entity.Question;


@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuizGameController {

    private final QuizApiService quizApiService;

    private final QuestionService questionService;

    @GetMapping("/question")
    public Question getQuestion(){
        return questionService.getRandomQuestion();
    }


}
