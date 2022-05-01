package project.baseball.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class NoRoomIdException extends RuntimeException {
  private String error;
}
