package project.baseball.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *.
 */

@Getter
@AllArgsConstructor
public class GameResultDto {
  private final int remainingCount;
  private final int answerCount;
}
