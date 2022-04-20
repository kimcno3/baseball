package project.baseball.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller.
 * DTO를 어떻게 나눌지 고민해볼 것.
   * Data : 모든 데이터를 담고 있을 엔티티
   * request
     * answer

   * Response
     * success, data{}
 * */
@Slf4j
@RestController
@RequestMapping("/game")
public class GameController {

  @PostMapping("/start")
  public ResponseEntity start() {
    return ResponseEntity.status(HttpStatus.OK).body("start");
  }

  @PostMapping("/{gameId}/answer")
  public ResponseEntity play(@PathVariable String gameId,
                             @RequestBody RequestAnswerDto answerDto) {
    log.info("gameId = {}, answer = {}", gameId, answerDto.getAnswer());
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