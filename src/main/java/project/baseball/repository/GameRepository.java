package project.baseball.repository;

import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;
import project.baseball.domain.GameResult;

/**
 * GameRepository.
 **/

public interface GameRepository {
  Long save(GameData data);

  GameData findById(Long id);

  GameData findByRoomId(String roomId);

  GameResult findResultByGameData(GameData gameData);

  void saveHistory(String roomId, GameHistory history);
}
