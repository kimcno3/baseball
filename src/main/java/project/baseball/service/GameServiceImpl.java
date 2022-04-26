package project.baseball.service;

import static project.baseball.utils.RandomStringMaker.makeAnswer;
import static project.baseball.utils.RandomStringMaker.makeRandomRoomId;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

  private GameData buildGameData(String answer) {
    GameData gameData = GameData.builder()
        .roomId(makeRandomRoomId())
        .answer(answer)
        .answerCount(0)
        .remainingCount(10)
        .build();
    return gameData;
  }

  private GameResult saveHistory(int[] count, String answer, String roomId) {
    int s = count[0];
    int b = count[1];
    int o = count[2];

    GameResult result = buildResult(s, b, o);
    GameHistory history = buildHistory(answer, result);
    gameRepository.saveHistory(roomId, history);
    return result;
  }

  private GameHistory buildHistory(String answer, GameResult result) {
    GameHistory history = GameHistory.builder()
        .answer(answer)
        .result(result)
        .build();
    return history;
  }

  private GameResult buildResult(int s, int b, int o) {
    // 결과값 객체에 담아서 리턴
    GameResult result = GameResult.builder()
        .strike(s)
        .ball(b)
        .out(o)
        .build();
    return result;
  }

  @Override
  public boolean playGame(String roomId, String answer) {
    GameData gameData = gameRepository.findByRoomId(roomId);
    int preRemainingCount = gameData.getRemainingCount();

    // 게임이 가능한 경우
    if (answer.length() == 3 && (0 < preRemainingCount && preRemainingCount <= 10)) {
      // 카운트 계산 로직
      int[] count = checkCount(gameData, answer);
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

  // 게임 로직 구현 시 필요한 메소드 -> 구분할 방법은?
  private int[] checkCount(GameData gameData, String answer) {
    String gameDataAnswer = gameData.getAnswer();
    String[] gameDataAnswerArray = splitAnswer(gameDataAnswer);
    String[] userAnswerArray = splitAnswer(answer);

    return checkCurrentCount(gameDataAnswer, userAnswerArray, gameDataAnswerArray);
  }

  private String[] splitAnswer(String answer) {
    return answer.split("");
  }

  private int[] checkCurrentCount(String gameDataAnswer,
                                  String[] userAnswerArray,
                                  String[] gameDataAnswerArray) {
    int s = 0;
    int b = 0;
    int o = 0;

    for (int i = 0; i < userAnswerArray.length; i++) {
      String userText = userAnswerArray[i];
      o = checkOutCount(gameDataAnswer, userText, o); // out
      for (int j = 0; j < gameDataAnswerArray.length; j++) {
        String gameDataText = gameDataAnswerArray[j];
        s = checkStrikeCount(gameDataText, userText, s, i, j); // strike
        b = checkBallCount(gameDataText, userText, b, i, j); // ball
      }
    }
    int[] count = {s, b, o};
    return count;
  }

  private int checkOutCount(String gameDataAnswer, String userText, int o) {
    if (!gameDataAnswer.contains(userText)) {
      o++;
    }
    return o;
  }

  private int checkStrikeCount(String gameDataText, String userText, int s, int i, int j) {
    if (gameDataText.equals(userText) && i == j) {
      s++;
    }
    return s;
  }

  private int checkBallCount(String gameDataText, String userText, int b, int i, int j) {
    if (gameDataText.equals(userText) && i != j) {
      b++;
    }
    return b;
  }
}
