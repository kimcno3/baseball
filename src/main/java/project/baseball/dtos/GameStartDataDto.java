package project.baseball.dtos;

import lombok.Getter;

/**
 * GameStartDto.
 * roomId 만을 담은 객체
 * 게임 시작 요청에 대한 응답을 보낼 때, data 객체에 해당
 */

@Getter
public class GameStartDataDto {
  private String roomId;

  public GameStartDataDto(String roomId) {
    this.roomId = roomId;
  }
}
