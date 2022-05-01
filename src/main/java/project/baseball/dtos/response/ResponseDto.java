package project.baseball.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.domain.GameData;
import project.baseball.dtos.response.data.AnswerData;
import project.baseball.dtos.response.data.HistoriesData;
import project.baseball.dtos.response.data.ResultData;
import project.baseball.dtos.response.data.StartData;

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
    return new ResponseDto(false, null, ErrorDto.from(code, message));
  }

  /** . */
  @Getter
  @AllArgsConstructor
  public static class ErrorDto {
    private String code;
    private String message;

    public static ErrorDto from(String code, String message) {
      return new ErrorDto(code, message);
    }
  }
}