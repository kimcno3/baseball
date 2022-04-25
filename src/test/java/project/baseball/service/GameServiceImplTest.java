package project.baseball.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.baseball.config.GameConfig;
import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;
import project.baseball.domain.GameResult;

@Slf4j
class GameServiceImplTest {

  ApplicationContext ac = new AnnotationConfigApplicationContext(GameConfig.class);

  @Test
  void playGame() {
    GameService gameService = ac.getBean(GameService.class);

    Long id = gameService.saveGameData();
    GameData gameData = gameService.findGameData(id);

    String roomId = gameData.getRoomId();

    String answer1 = "123";
    String answer2 = "456";

    gameService.playGame(roomId, answer1);
    gameService.playGame(roomId, answer2);

    log.info("realAnswer {}", gameData.getAnswer());
    GameHistory history = gameData.getHistories().get(0);
    String answer = history.getAnswer();
    GameResult result = history.getResult();
    int strike = result.getStrike();
    int ball = result.getBall();
    int out = result.getOut();
    log.info("answer {}, strike {}, ball {}, out {}", answer, strike, ball, out);

    history = gameData.getHistories().get(1);
    answer = history.getAnswer();
    result = history.getResult();
    strike = result.getStrike();
    ball = result.getBall();
    out = result.getOut();
    log.info("answer {}, strike {}, ball {}, out {}", answer, strike, ball, out);
  }

  @Test
  void randomString() {
    for (int x = 0; x < 20; x++) {
      String answer = "";
      while (answer.length() < 3) {
        String randomString = RandomStringUtils.random(1, '1', '9', false, true);
        if (answer.length() == 0 || !answer.contains(randomString)) {
          answer += randomString;
        }
      }
      log.info("answer = {}", answer);
    }
  }
}