package project.baseball.domain;

import static project.baseball.utils.RandomStringMaker.makeRandomRoomId;

import java.util.ArrayList;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import project.baseball.utils.RandomStringMaker;

/**
 * .
  **/

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
        .roomId(makeRandomRoomId())
        .answer(answer)
        .answerCount(0)
        .remainingCount(10)
        .build();
    return gameData;
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
