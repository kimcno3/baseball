package project.baseball.dtos.response.data;


import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;

/** . */

@Getter
@AllArgsConstructor
public class HistoriesData {
  private Collection<GameHistory> histories;

  public static HistoriesData from(GameData gameData) {
    return new HistoriesData(gameData.getHistories());
  }
}
