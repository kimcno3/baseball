package project.baseball.repository;

import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;
import project.baseball.domain.GameResult;

/**
 * GameRepository.
 **/

public interface GameRepository {
  GameData save(GameData data);

  GameData findByRoomId(String roomId);

  GameData saveHistory(GameData gameData, GameHistory history);
}
