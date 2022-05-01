package project.baseball.domain;

import java.util.ArrayList;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import project.baseball.exception.GameClosedException;
import project.baseball.utils.RandomStringMaker;

/**
 * .
  **/

@Slf4j
@Entity
@Getter
public class GameData {
  private boolean correct;
  private String roomId;
  private String answer;
  private int remainingCount;
  private int answerCount;
  private ArrayList<GameHistory> histories = new ArrayList<>();

  /**
   * .
   */

  @Builder
  public GameData(boolean correct, String roomId, String answer,
                  int remainingCount, int answerCount) {
    this.correct = correct;
    this.roomId = roomId;
    this.answer = answer;
    this.remainingCount = remainingCount;
    this.answerCount = answerCount;
  }

  /** . */

  public static GameData buildGameData() {
    String answer = RandomStringMaker.makeAnswer();
    GameData gameData = GameData.builder()
        .correct(false)
        .roomId(RandomStringMaker.makeRandomRoomId())
        .answer(answer)
        .answerCount(0)
        .remainingCount(10)
        .build();
    return gameData;
  }

  /** . */

  public GameHistory checkCount(String answer) {
    if (!this.isCorrect() && isCheckCount()) {
      int[] count = checkCountLogic(answer);
      return GameHistory.makeHistory(count, answer);
    }
    throw new GameClosedException("게임이 종료되었습니다.");
  }

  private boolean isCheckCount() {
    if (0 < this.remainingCount && this.remainingCount <= 10) {
      return true;
    }
    return false;
  }

  private int[] checkCountLogic(String answer) {
    this.plusAnswerCount();
    this.minusRemainingCount();

    String gameDataAnswer = this.getAnswer();
    String[] gameDataAnswerArray = splitAnswer(gameDataAnswer);
    String[] userAnswerArray = splitAnswer(answer);
    return checkCurrentCount(gameDataAnswer, userAnswerArray, gameDataAnswerArray);
  }

  private int plusAnswerCount() {
    return this.answerCount += 1;
  }

  private int minusRemainingCount() {
    return this.remainingCount -= 1;
  }

  private static String[] splitAnswer(String answer) {
    return answer.split("");
  }

  private int[] checkCurrentCount(String gameDataAnswer,
                                  String[] userAnswerArray, String[] gameDataAnswerArray) {
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
    this.checkAnswer(s);
    return s;
  }

  private int checkBallCount(String gameDataText, String userText, int b, int i, int j) {
    if (gameDataText.equals(userText) && i != j) {
      b++;
    }
    return b;
  }

  private void checkAnswer(int s) {
    if (s == 3) {
      this.correct = true;
    }
  }

  public GameData addHistory(GameHistory history) {
    this.getHistories().add(this.getHistories().size(), history);
    return this;
  }

  public GameHistory getLastHistory() {
    return this.getHistories().get(this.getHistories().size() - 1);
  }
}
