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
import project.baseball.dtos.GameStartDataDto;
import project.baseball.dtos.RequestAnswerDto;
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

  @PostMapping("/{gameId}/answer")
  public ResponseEntity play(@PathVariable String gameId,
                             @RequestBody RequestAnswerDto answerDto) {
    log.info("gameId = {}, answer = {}", gameId, answerDto.getAnswer().getClass());
    return ResponseEntity.status(HttpStatus.OK).body("answer");
  }

  @GetMapping("/{gameId}")
  public ResponseEntity result(@PathVariable String gameId) {
    log.info("gameId = {}", gameId);
    return ResponseEntity.status(HttpStatus.OK).body("result");
  }

  @GetMapping("/{gameId}/history")
  public ResponseEntity history(@PathVariable String gameId) {
    log.info("gameId = {}", gameId);
    return ResponseEntity.status(HttpStatus.OK).body("history");
  }
}
