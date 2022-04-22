package project.baseball.repository;

import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;

/**
 * GameRepository.
 **/

public interface GameRepository {
  Long save(GameData data);

  GameData findById(Long id);

  GameData findByRoomId(String roomId);

  void saveHistory(String roomId, GameHistory history);
}
