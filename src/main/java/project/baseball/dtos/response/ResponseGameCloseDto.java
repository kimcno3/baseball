package project.baseball.dtos.response;

import lombok.Getter;

/**
 * .
 */

@Getter
public class ResponseGameCloseDto<T, S> extends ResponseGameDto<T> {
  private S error;

  public ResponseGameCloseDto(boolean success, T data, S error) {
    super(success, data);
    this.error = error;
  }
}
