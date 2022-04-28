package project.baseball.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class GameClosedException extends RuntimeException {
  private String error;
}
