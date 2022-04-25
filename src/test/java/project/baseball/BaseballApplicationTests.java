package project.baseball;

//import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.baseball.config.GameConfig;
import project.baseball.controller.GameController;
import project.baseball.repository.GameRepository;
import project.baseball.repository.GameRepositoryImpl;
import project.baseball.service.GameService;
import project.baseball.service.GameServiceImpl;

@Slf4j
@SpringBootTest
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

    // assertThat(gameController.getClass()).isEqualTo(GameController.class);
    // assertThat(gameService.getClass()).isEqualTo(GameServiceImpl.class);
    // assertThat(gameRepository.getClass()).isEqualTo(GameRepositoryImpl.class);
  }

  @Test
  void atomicToInt() {
    int value = 10;
    AtomicInteger atomicInteger = new AtomicInteger(10);
    int integer = atomicInteger.get();
    // assertThat(integer).isEqualTo(value);
  }
}
