package project.baseball.repository;

import project.baseball.domain.GameData;

/**
 * GameRepository.
 **/

public interface GameRepository {
  Long save(GameData data);

  GameData findById(Long id);

  GameData findByRoomId(String roomId);
}
