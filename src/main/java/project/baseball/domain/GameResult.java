package project.baseball.domain;

import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

/**
 * Result.
 * History 객체 각각에 할당될 answer 하나 당 결과 값
 **/

@Entity
@Getter
public class GameResult {
  private int strike;
  private int ball;
  private int out;

  /**
   *.
   */

  @Builder
  public GameResult(int strike, int ball, int out) {
    this.strike = strike;
    this.ball = ball;
    this.out = out;
  }
}
