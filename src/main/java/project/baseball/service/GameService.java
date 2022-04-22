package project.baseball.service;

import project.baseball.domain.GameData;
import project.baseball.domain.GameResult;

/**
 * GameService.
 **/

public interface GameService {
  Long saveGameData();

  GameResult saveHistory(int[] count, String answer, String roomId);

  GameData findGameData(Long id);

  GameData findGameData(String gameId);

  GameResult playGame(String roomId, String answer);
}
