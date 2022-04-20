package project.baseball.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * History.
 * Data 엔티티 내에서 histories에 할당될 객체
 **/

@Getter
@Setter
public class GameHistory {
  private String answer;
  private GameResult result;

}
