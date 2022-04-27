package project.baseball.dtos.response;

import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;
import project.baseball.domain.GameResult;
import project.baseball.dtos.GameHistoriesDto;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class ResponseGameDto<T> {
  private boolean success;
  private T data;
  private Error error;

  public static ResponseGameDto successStart(GameData gameData) {
    return new ResponseGameDto(true, new GameStartData(gameData.getRoomId()), null);
  }

  /** . */

  public static ResponseGameDto successAnswer(GameData gameData) {
    GameResult result = gameData.getHistories().get(gameData.getHistories().size() - 1).getResult();
    boolean correct = gameData.isCorrect();
    int remainingCount = gameData.getRemainingCount();
    int s = result.getStrike();
    int b = result.getBall();
    int o = result.getOut();
    return new ResponseGameDto(true, new GameAnswerData(correct, remainingCount, s, b, o), null);
  }

  /** . */

  public static ResponseGameDto failAnswer() {
    return new ResponseGameDto(false, null, new Error("CLOSED_GAME", ""));
  }

  /** . */

  public static ResponseGameDto successResult(GameData gameData) {
    return new ResponseGameDto(true,
           new GameResultData(gameData.getRemainingCount(), gameData.getAnswerCount()), null);
  }

  /** . */

  public static ResponseGameDto successHistories(GameData gameData) {
    return new ResponseGameDto(true, new GameHistoriesData(gameData.getHistories()), null);
  }

  /** . */

  @Getter
  @AllArgsConstructor
  public static class GameStartData {
    private String roomId;
  }

  /** . */

  @Getter
  @AllArgsConstructor
  public static class GameAnswerData {
    private boolean correct;
    private int remainingCount;
    private int strike;
    private int ball;
    private int out;
  }

  /** . */

  @Getter
  @AllArgsConstructor
  public static class Error {
    private String code;
    private String message;
  }

  /** . */

  @Getter
  @AllArgsConstructor
  public static class GameResultData {
    private int remainingCount;
    private int answerCount;
  }

  /** . */

  @Getter
  @AllArgsConstructor
  public static class GameHistoriesData {
    private Collection<GameHistory> histories;
  }
}
