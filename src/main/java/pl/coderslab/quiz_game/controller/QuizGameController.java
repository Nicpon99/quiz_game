package pl.coderslab.quiz_game.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.quiz_game.Service.QuizApiService;


@Controller
@RequiredArgsConstructor
public class QuizGameController {

    private final QuizApiService quizApiService;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        quizApiService.fetchAndSaveQuestions();
        return "hello world !!!";
    }
}
