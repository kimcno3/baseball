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
  public GameData findGameData(Long id) {
    return gameRepository.findById(id);
  }

  @Override
  public GameData findGameData(String roomId) {
    return gameRepository.findByRoomId(roomId);
  }

  @Override
  public GameResult findResult(String roomId) {
    GameData gameData = gameRepository.findByRoomId(roomId);
    return gameRepository.findResultByGameData(gameData);
  }

  @Override
  public Long saveGameData() {
    String answer = makeAnswer();
    GameData gameData = buildGameData(answer);
    return gameRepository.save(gameData);
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

  GameResult saveHistory(int[] count, String answer, String roomId) {
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
  public boolean playGame(String roomId, String answer) {
    int[] count = {0, 0, 0};

    GameData gameData = gameRepository.findByRoomId(roomId);
    int preRemainingCount = gameData.getRemainingCount();

    // 게임이 가능한 경우
    if (answer.length() == 3 && (0 < preRemainingCount && preRemainingCount <= 10)) {
      // 카운트 계산 로직
      checkCount(gameData, answer, count);
      // 결과값 저장
      GameResult result = saveHistory(count, answer, roomId);

      // 답변수, 남은 횟수 수정
      gameData.plusAnswerCount();
      gameData.minusRemainingCount();

      // 게임이 끝난 경우 - 정답 or 답변 횟수 10번 초과
      int remainingCount = gameData.getRemainingCount();
      int s = result.getStrike();
      if (s == 3 || remainingCount <= 0) {
        return false;
      }
      // 게임 계속 진행
      return true;
    }
    // 게임이 불가능한 경우
    throw new RuntimeException("게임 진행 불가");
  }

  // 랜덤 roomId, answer 생성 코드 -> util 패키지로 구분
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

  String makeRandomString() {
    return RandomStringUtils.random(1, '1', '9', false, true);
  }

  String makeRandomRoomId() {
    return RandomStringUtils.random(3, 'A', 'Z', true, false);
  }

  // 게임 로직 구현 시 필요한 메소드 -> 구분할 방법은?
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
