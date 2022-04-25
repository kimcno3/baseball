package project.baseball.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class GameAnswerErrorDto {
  private String code;
  private String message;
}
