package project.baseball.service;

import project.baseball.domain.GameData;
import project.baseball.domain.GameResult;

/**
 * GameService.
 **/

public interface GameService {
  Long saveGameData();

  GameData findGameData(Long id);

  GameData findGameData(String gameId);

  GameResult findResult(String roomId);

  boolean playGame(String roomId, String answer);
}
