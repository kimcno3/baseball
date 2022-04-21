package project.baseball.controller;

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
import project.baseball.domain.GameResult;
import project.baseball.dtos.GameAnswerDataDto;
import project.baseball.dtos.GameStartDataDto;
import project.baseball.dtos.RequestAnswerDto;
import project.baseball.dtos.ResponseAnswerDto;
import project.baseball.dtos.ResponseGameStartDto;
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
    Long id = gameService.save();
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
    if (0 < gameData.getRemainingCount() && gameData.getRemainingCount() <= 10) {
      GameResult result = gameService.playGame(roomId, answerDto.getAnswer());
      if (result.getStrike() == 3 || gameData.getRemainingCount() == 0) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Closed");
      }
      int remainingCount = gameData.getRemainingCount();
      int strike = result.getStrike();
      int ball = result.getBall();
      int out = result.getOut();

      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseAnswerDto(
              true, new GameAnswerDataDto(
                  false, remainingCount, strike, ball, out)));
    }
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("X");
  }

  @GetMapping("/{roomId}")
  public ResponseEntity result(@PathVariable String gameId) {
    log.info("roomId = {}", gameId);
    return ResponseEntity.status(HttpStatus.OK).body("result");
  }

  @GetMapping("/{roomId}/history")
  public ResponseEntity history(@PathVariable String gameId) {
    log.info("roomId = {}", gameId);
    return ResponseEntity.status(HttpStatus.OK).body("history");
  }
}
