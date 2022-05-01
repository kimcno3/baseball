package project.baseball.domain;

import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

/**
 * History.
 * Data 엔티티 내에서 histories에 할당될 객체
 **/

@Entity
@Getter
public class GameHistory {
  private String answer;
  private GameResult result;

  @Builder
  public GameHistory(String answer, GameResult result) {
    this.answer = answer;
    this.result = result;
  }

  /** . */

  public static GameHistory buildHistory(String answer, GameResult result) {
    GameHistory history = GameHistory.builder()
        .answer(answer)
        .result(result)
        .build();
    return history;
  }

  /** . */

  public static GameHistory makeHistory(int[] count, String answer) {
    int s = count[0];
    int b = count[1];
    int o = count[2];

    GameResult result = GameResult.buildResult(s, b, o);
    GameHistory history = GameHistory.buildHistory(answer, result);
    return history;
  }

}
