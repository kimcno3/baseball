package project.baseball.domain;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.Setter;

/**
 * Result.
 * History 객체 각각에 할당될 answer 하나 당 결과 값
 **/

@Getter
@Setter
public class GameResult {
  private AtomicInteger strike;
  private AtomicInteger ball;
  private AtomicInteger out;
}
