package project.baseball.domain;

import static project.baseball.utils.RandomStringMaker.makeRandomRoomId;

import java.util.ArrayList;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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
    if (this.isCorrect()) {
      this.remainingCount = 0;
    }
    if (0 < this.remainingCount && this.remainingCount <= 10) {
      int[] count = checkCountLogic(this, answer);
      return GameHistory.makeHistory(count, answer);
    }
    return null;
  }

  private int[] checkCountLogic(GameData gameData, String answer) {
    gameData.plusAnswerCount();
    gameData.minusRemainingCount();
    String gameDataAnswer = gameData.getAnswer();
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

  private String[] splitAnswer(String answer) {
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

  private void checkAnswer(int s) {
    if (s == 3) {
      this.correct = true;
    }
  }

  private int checkBallCount(String gameDataText, String userText, int b, int i, int j) {
    if (gameDataText.equals(userText) && i != j) {
      b++;
    }
    return b;
  }
}
