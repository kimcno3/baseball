package project.baseball.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;
import project.baseball.domain.GameResult;
import project.baseball.repository.GameRepository;

/**
 * Service 구현체.
 *
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

  private final GameRepository gameRepository;

  @Override
  public Long saveGameData() {
    String answer = makeAnswer();
    GameData gameData = buildGameData(answer);
    return gameRepository.save(gameData);
  }

  String makeAnswer() {
    String answer = "";
    while (answer.length() < 3) {
      String randomString = makeRandomString();
      if (answer.length() == 0 || !answer.contains(randomString)) {
        answer += randomString;
      }
    }
    return answer;
  }

  GameData buildGameData(String answer) {
    GameData gameData = GameData.builder()
        .roomId(makeRandomRoomId())
        .answer(answer)
        .answerCount(0)
        .remainingCount(10)
        .build();
    return gameData;
  }

  String makeRandomString() {
    return RandomStringUtils.random(1, '1', '9', false, true);
  }

  String makeRandomRoomId() {
    return RandomStringUtils.random(3, 'A', 'Z', true, false);
  }

  @Override
  public GameResult saveHistory(int[] count, String answer, String roomId) {
    int s = count[0];
    int b = count[1];
    int o = count[2];

    GameResult result = buildResult(s, b, o);
    GameHistory history = buildHistory(answer, result);
    gameRepository.saveHistory(roomId, history);
    return result;
  }

  GameResult buildResult(int s, int b, int o) {
    // 결과값 객체에 담아서 리턴
    GameResult result = GameResult.builder()
        .strike(s)
        .ball(b)
        .out(o)
        .build();
    return result;
  }

  GameHistory buildHistory(String answer, GameResult result) {
    GameHistory history = GameHistory.builder()
        .answer(answer)
        .result(result)
        .build();
    return history;
  }

  @Override
  public GameData findGameData(Long id) {
    return gameRepository.findById(id);
  }

  @Override
  public GameData findGameData(String roomId) {
    return gameRepository.findByRoomId(roomId);
  }

  @Override
  public GameResult playGame(String roomId, String answer) {
    GameData gameData = gameRepository.findByRoomId(roomId);
    int[] count = {0, 0, 0};
    // 카운트 계산 로직
    checkCount(gameData, answer, count);
    // 결과값 저장 및 리턴
    return saveHistory(count, answer, roomId);
  }

  void checkCount(GameData gameData, String answer, int[] count) {
    String gameDataAnswer = gameData.getAnswer();
    String[] gameDataAnswerArray = splitAnswer(gameDataAnswer);
    String[] userAnswerArray = splitAnswer(answer);

    for (int i = 0; i < userAnswerArray.length; i++) {
      String userText = userAnswerArray[i];
      checkOutCount(gameDataAnswer, userText, count); // out
      for (int j = 0; j < gameDataAnswerArray.length; j++) {
        String gameDataText = gameDataAnswerArray[j];
        checkStrikeAndBallCount(gameDataText, userText, count, i, j); // strike, ball
      }
    }
  }

  String[] splitAnswer(String answer) {
    return answer.split("");
  }

  void checkOutCount(String gameDataAnswer, String userText, int[] count) {
    if (!gameDataAnswer.contains(userText)) {
      count[2]++;
    }
  }

  void checkStrikeAndBallCount(String gameDataText, String userText, int[] count, int i, int j) {
    if (gameDataText.equals(userText)) {
      if (i == j) {
        count[0]++;
      } else {
        count[1]++;
      }
    }
  }
}
