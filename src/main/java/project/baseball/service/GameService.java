package project.baseball.service;

import project.baseball.domain.GameData;
import project.baseball.domain.GameResult;

/**
 * GameService.
 **/

public interface GameService {
  GameData saveGameData();

  GameData findGameData(String gameId);

  GameData playGame(String roomId, String answer);
}
