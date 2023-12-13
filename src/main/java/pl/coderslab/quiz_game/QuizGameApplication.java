package pl.coderslab.quiz_game;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.coderslab.quiz_game.Service.QuizApiService;

@SpringBootApplication
@RequiredArgsConstructor
public class QuizGameApplication implements CommandLineRunner {

    private final QuizApiService quizApiService;

    public static void main(String[] args) {
        SpringApplication.run(QuizGameApplication.class, args);
    }

    @Override
    public void run(String...args) throws Exception {
        quizApiService.fetchAndSaveQuestions();
    }
}
