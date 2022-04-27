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
import project.baseball.dtos.response.ResponseGameDto;
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
  public ResponseGameDto start() {
    GameData gameData = gameService.saveGameData();
    return ResponseGameDto.successStart(gameData);
  }

  /**
   *.
   */

  @PostMapping("/{roomId}/answer")
  public ResponseGameDto play(@PathVariable String roomId,
                              @RequestBody RequestAnswerDto answerDto) {
    GameData gameData = gameService.playGame(roomId, answerDto.getAnswer());
    if (!gameData.isCorrect()) {
      return ResponseGameDto.successAnswer(gameData);
    } else {
      return ResponseGameDto.failAnswer();
    }
  }

  /**
   *.
   */

  @GetMapping("/{roomId}")
  public ResponseGameDto result(@PathVariable String roomId) {
    GameData gameData = gameService.findGameData(roomId);
    return ResponseGameDto.successResult(gameData);
  }

  /**
   *.
   */

  @GetMapping("/{roomId}/history")
  public ResponseGameDto history(@PathVariable String roomId) {
    GameData gameData = gameService.findGameData(roomId);
    return ResponseGameDto.successHistories(gameData);
  }
}