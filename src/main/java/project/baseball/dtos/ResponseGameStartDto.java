package project.baseball.dtos;

import lombok.Getter;

/**
 * ResponseGameStartDto.
 * 게임시작 url이 오면, 성공 여부와 생성된 GameData 엔티티 중 roomId만 담은 객체를 담아 Response
**/

@Getter
public class ResponseGameStartDto {
  private boolean success;
  private GameStartDataDto data;

  public ResponseGameStartDto(boolean success, GameStartDataDto data) {
    this.success = success;
    this.data = data;
  }
}
