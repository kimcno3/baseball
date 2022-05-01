package project.baseball.dtos.response.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.domain.GameData;

/** . */

@Getter
@AllArgsConstructor
public class StartData {
  private String roomId;

  public static StartData from(GameData gameData) {
    return new StartData(gameData.getRoomId());
  }
}
