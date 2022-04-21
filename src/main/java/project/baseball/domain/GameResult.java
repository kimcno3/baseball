package project.baseball.domain;

import lombok.Getter;

/**
 * Result.
 * History 객체 각각에 할당될 answer 하나 당 결과 값
 **/

@Getter
public class GameResult {
  private int strike;
  private int ball;
  private int out;
}
