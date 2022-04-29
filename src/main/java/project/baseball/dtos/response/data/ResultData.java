package project.baseball.dtos.response.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.domain.GameData;

/** . */

@Getter
@AllArgsConstructor
public class ResultData {
  private int remainingCount;
  private int answerCount;

  public static ResultData from(GameData gameData) {
    return new ResultData(gameData.getRemainingCount(), gameData.getAnswerCount());
  }
}