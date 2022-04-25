package project.baseball;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import project.baseball.config.GameConfig;
import project.baseball.controller.GameController;
import project.baseball.repository.GameRepository;
import project.baseball.service.GameService;

@Slf4j
@SpringBootTest
@Import(GameConfig.class)
class BaseballApplicationTests {

  ApplicationContext ac = new AnnotationConfigApplicationContext(GameConfig.class);

  @Test
  void contextLoads() {
  }

  @Test
  void test1() {
    GameController gameController = ac.getBean(GameController.class);
    GameService gameService = ac.getBean(GameService.class);
    GameRepository gameRepository = ac.getBean(GameRepository.class);
    log.info("gameController = {}, gameService = {}, gameRepository = {}",
              gameController, gameService, gameRepository);
  }
}
