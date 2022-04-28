package project.baseball.dtos.response;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;
import project.baseball.domain.GameResult;

/**
 * .
 */

@Getter
@AllArgsConstructor
public class ResponseDto<T, S> {
  private boolean success;
  private T data;
  private S error;

  public static ResponseDto successStart(GameData gameData) {
    return new ResponseDto(true, StartData.from(gameData), null);
  }

  public static ResponseDto successAnswer(GameData gameData) {
    return new ResponseDto(true, AnswerData.from(gameData), null);
  }

  public static ResponseDto successResult(GameData gameData) {
    return new ResponseDto(true, ResultData.from(gameData), null);
  }

  public static ResponseDto successHistories(GameData gameData) {
    return new ResponseDto(true, HistoriesData.from(gameData), null);
  }

  public static ResponseDto fail(String code, String message) {
    return new ResponseDto(false, null, Error.from(code, message));
  }

  /** . */

  @Getter
  @AllArgsConstructor
  public static class StartData {
    private String roomId;

    private static StartData from(GameData gameData) {
      return new StartData(gameData.getRoomId());
    }
  }

  /** . */

  @Getter
  @AllArgsConstructor
  public static class AnswerData {
    private boolean correct;
    private int remainingCount;
    private int strike;
    private int ball;
    private int out;

    private static AnswerData from(GameData gameData) {
      GameHistory gameHistory = gameData.getHistories().get(gameData.getHistories().size() - 1);
      GameResult result = gameHistory.getResult();

      boolean correct = gameData.isCorrect();
      int remainingCount = gameData.getRemainingCount();
      int s = result.getStrike();
      int b = result.getBall();
      int o = result.getOut();

      return new AnswerData(correct, remainingCount, s, b, o);
    }
  }

  /** . */

  @Getter
  @AllArgsConstructor
  public static class ResultData {
    private int remainingCount;
    private int answerCount;

    private static ResultData from(GameData gameData) {
      return new ResultData(gameData.getRemainingCount(), gameData.getAnswerCount());
    }
  }

  /** . */

  @Getter
  @AllArgsConstructor
  public static class HistoriesData {
    private Collection<GameHistory> histories;

    private static HistoriesData from(GameData gameData) {
      return new HistoriesData(gameData.getHistories());
    }
  }

  /** . */

  @Getter
  @AllArgsConstructor
  public static class Error {
    private String code;
    private String message;

    public static Error from(String code, String message) {
      return new Error(code, message);
    }
  }
}