package project.baseball.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class ResponseCloseGameDto {
  private boolean success;
  private GameAnswerDataDto data;
  private GameAnswerErrorDto error;
}
