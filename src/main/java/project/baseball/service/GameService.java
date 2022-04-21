package project.baseball.service;

import project.baseball.domain.GameData;

/**
 * GameService.
 **/

public interface GameService {
  Long save();

  GameData findGameData(Long id);
}
