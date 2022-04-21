package project.baseball.domain;

import lombok.Getter;

/**
 * History.
 * Data 엔티티 내에서 histories에 할당될 객체
 **/

@Getter
public class GameHistory {
  private String answer;
  private GameResult result;

}
