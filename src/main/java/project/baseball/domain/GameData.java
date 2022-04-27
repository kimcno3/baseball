package project.baseball.domain;

import java.util.ArrayList;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

/**
 * .
  **/

@Entity
@Getter
public class GameData {
  private String roomId;
  private String answer;
  private int remainingCount;
  private int answerCount;
  private boolean correct;
  private ArrayList<GameHistory> histories = new ArrayList<>();

  /**
   * .
   */

  @Builder
  public GameData(String roomId, String answer, int remainingCount, int answerCount) {
    this.roomId = roomId;
    this.answer = answer;
    this.remainingCount = remainingCount;
    this.answerCount = answerCount;
  }

  public boolean isCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }

  public int plusAnswerCount() {
    return this.answerCount += 1;
  }

  public int minusRemainingCount() {
    return this.remainingCount -= 1;
  }
}
