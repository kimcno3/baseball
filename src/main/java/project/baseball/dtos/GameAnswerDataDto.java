package project.baseball.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *.
 */

@Getter
@AllArgsConstructor
public class GameAnswerDataDto {

  private boolean correct;
  private int remainingCount;
  private int strike;
  private int ball;
  private int out;
}
