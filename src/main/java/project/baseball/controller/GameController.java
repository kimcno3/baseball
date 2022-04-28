package project.baseball.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.baseball.domain.GameData;
import project.baseball.dtos.request.RequestAnswerDto;
import project.baseball.dtos.response.ResponseDto;
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
  public ResponseDto start() {
    GameData gameData = gameService.saveGameData();
    log.info("answer = {}", gameData.getAnswer());
    return ResponseDto.successStart(gameData);
  }

  /**
   *.
   */

  @PostMapping("/{roomId}/answer")
  public ResponseDto play(@PathVariable String roomId, @RequestBody RequestAnswerDto answerDto) {
    GameData gameData = gameService.playGame(roomId, answerDto.getAnswer());
    return ResponseDto.responsePlay(gameData);
  }

  /**
   *.
   */

  @GetMapping("/{roomId}")
  public ResponseDto result(@PathVariable String roomId) {
    GameData gameData = gameService.findGameData(roomId);
    return ResponseDto.successResult(gameData);
  }

  /**
   *.
   */

  @GetMapping("/{roomId}/history")
  public ResponseDto history(@PathVariable String roomId) {
    GameData gameData = gameService.findGameData(roomId);
    return ResponseDto.successHistories(gameData);
  }
}