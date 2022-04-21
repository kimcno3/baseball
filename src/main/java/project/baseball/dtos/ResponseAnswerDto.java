package project.baseball.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *.
 */

@Getter
@AllArgsConstructor
public class ResponseAnswerDto {
  private boolean success;
  private GameAnswerDataDto data;
}
