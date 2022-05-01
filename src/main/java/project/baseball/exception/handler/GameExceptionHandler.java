package project.baseball.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.baseball.controller.GameController;
import project.baseball.dtos.response.ResponseDto;
import project.baseball.exception.GameClosedException;
import project.baseball.exception.NoRoomIdException;

/**
 * .
 */

@RestControllerAdvice(basePackageClasses = GameController.class)
public class GameExceptionHandler {
  @ExceptionHandler(NoRoomIdException.class)
  public ResponseDto noRoomIdExceptionHandler(NoRoomIdException e) {
    return ResponseDto.fail("NO_ROOM_ID_EXISTED", e.getError());
  }

  @ExceptionHandler(GameClosedException.class)
  public ResponseDto gameClosedExceptionHandler(GameClosedException e) {
    return ResponseDto.fail("GAME_CLOSED", e.getError());
  }
}
