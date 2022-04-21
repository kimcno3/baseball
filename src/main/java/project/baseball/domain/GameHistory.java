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
}
