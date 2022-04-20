package project.baseball.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * ResponseRoomIdDto.
**/

@Setter
@Getter
public class ResponseRoomIdDto {
  private String roomId;

  public ResponseRoomIdDto(String roomId) {
    this.roomId = roomId;
  }
}
