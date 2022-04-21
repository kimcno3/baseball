package project.baseball.service;

import project.baseball.domain.GameData;
import project.baseball.domain.GameResult;

/**
 * GameService.
 **/

public interface GameService {
  Long save();

  GameData findGameData(Long id);

  GameData findGameData(String gameId);

  GameResult playGame(String roomId, String answer);
}
