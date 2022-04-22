package project.baseball.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.dtos.GameAnswerDataDto;
import project.baseball.dtos.GameAnswerErrorDto;

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
