package project.baseball.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class ResponseGameDto<T> {
  private boolean success;
  private T data;
}
