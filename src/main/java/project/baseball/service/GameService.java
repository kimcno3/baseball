package project.baseball.service;

import project.baseball.domain.GameData;
import project.baseball.domain.GameResult;

/**
 * GameService.
 **/

public interface GameService {
  GameData saveGameData();

  GameData findGameData(Long id);

  GameData findGameData(String gameId);

  GameResult findResult(String roomId);

  GameData playGame(String roomId, String answer);
}
