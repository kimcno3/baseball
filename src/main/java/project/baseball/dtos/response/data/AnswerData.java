package project.baseball.dtos.response.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;
import project.baseball.domain.GameResult;

/** . */

@Getter
@AllArgsConstructor
public class AnswerData {
  private boolean correct;
  private int remainingCount;
  private int strike;
  private int ball;
  private int out;

  /** .
   *
   * */

  public static AnswerData from(GameData gameData) {
    GameHistory gameHistory = gameData.getLastHistory();
    GameResult result = gameHistory.getResult();

    boolean correct = gameData.isCorrect();
    int remainingCount = gameData.getRemainingCount();
    int s = result.getStrike();
    int b = result.getBall();
    int o = result.getOut();

    return new AnswerData(correct, remainingCount, s, b, o);
  }
}
