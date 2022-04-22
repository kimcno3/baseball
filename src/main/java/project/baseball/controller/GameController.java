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
    int preRemainingCount = gameData.getRemainingCount();

    // 게임이 가능한 경우
    if (answerDto.getAnswer().length() == 3 && (0 < preRemainingCount && preRemainingCount <= 10)) {
      GameResult result = gameService.playGame(roomId, answerDto.getAnswer());
      int remainingCount = gameData.getRemainingCount();
      int s = result.getStrike();
      int b = result.getBall();
      int o = result.getOut();

      // 게임이 끝난 경우 - 정답
      if (result.getStrike() == 3) {
        return ResponseEntity.status(HttpStatus.OK).body("correct");
      }

      // 게임이 끝난 경우 - 답변 횟수 10번 초과
      if (remainingCount <= 0) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseCloseGameDto(false, null,
                new GameAnswerErrorDto("CLOSED_GAME", "")));
      }
      // 게임 계속 진행
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseAnswerDto(true,
              new GameAnswerDataDto(false, remainingCount, s, b, o)));
    }
    throw new RuntimeException("새로운 예외");
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