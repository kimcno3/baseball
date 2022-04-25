package project.baseball.controller;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;
import project.baseball.domain.GameResult;
import project.baseball.dtos.GameAnswerDataDto;
import project.baseball.dtos.GameAnswerErrorDto;
import project.baseball.dtos.GameHistoriesDto;
import project.baseball.dtos.GameResultDto;
import project.baseball.dtos.GameStartDataDto;
import project.baseball.dtos.request.RequestAnswerDto;
import project.baseball.dtos.response.ResponseAnswerDto;
import project.baseball.dtos.response.ResponseCloseGameDto;
import project.baseball.dtos.response.ResponseGameHistories;
import project.baseball.dtos.response.ResponseGameStartDto;
import project.baseball.dtos.response.ResponseResultDto;
import project.baseball.service.GameService;

/**
 * Controller.
 **/

@Slf4j
@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

  private final GameService gameService;

  /**
   * .
   */

  @PostMapping("/start")
  public ResponseEntity start() {
    Long id = gameService.saveGameData();
    GameData gameData = gameService.findGameData(id);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new ResponseGameStartDto(true, new GameStartDataDto(gameData.getRoomId())));
  }

  /**
   *.
   */

  @PostMapping("/{roomId}/answer")
  public ResponseEntity play(@PathVariable String roomId, @RequestBody RequestAnswerDto answerDto) {
    GameData gameData = gameService.findGameData(roomId);
    boolean isSuccess = gameService.playGame(roomId, answerDto.getAnswer());

    if (isSuccess) {
      // 게임이 안끝난 경우 - 아직 기회가 남은 경우
      GameResult result = gameService.findResult(roomId);
      int remainingCount = gameData.getRemainingCount();
      int s = result.getStrike();
      int b = result.getBall();
      int o = result.getOut();

      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseAnswerDto(isSuccess,
              new GameAnswerDataDto(false, remainingCount, s, b, o)));
    } else {
      // 게임이 끝난 경우 - 정답을 맞췄거나 기회를 다 소모한 경우
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseCloseGameDto(isSuccess, null,
              new GameAnswerErrorDto("CLOSED_GAME", "")));
    }
  }

  /**
   *.
   */

  @GetMapping("/{roomId}")
  public ResponseEntity result(@PathVariable String roomId) {
    GameData gameData = gameService.findGameData(roomId);
    int remainingCount = gameData.getRemainingCount();
    int answerCount = gameData.getAnswerCount();
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new ResponseResultDto(true, new GameResultDto(remainingCount, answerCount)));
  }

  /**
   *.
   */

  @GetMapping("/{roomId}/history")
  public ResponseEntity history(@PathVariable String roomId) {
    GameData gameData = gameService.findGameData(roomId);
    Collection<GameHistory> histories = gameData.getHistories();
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new ResponseGameHistories(true, new GameHistoriesDto(histories)));
  }
}