package project.baseball.service;

import static project.baseball.utils.RandomStringMaker.makeAnswer;
import static project.baseball.utils.RandomStringMaker.makeRandomRoomId;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;
import project.baseball.domain.GameResult;
import project.baseball.repository.GameRepository;
import project.baseball.repository.GameRepositoryImpl;
import project.baseball.utils.RandomStringMaker;

/**
 * Service 구현체.
 *
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

  private final GameRepository gameRepository;

  @Override
  public GameData findGameData(String roomId) {
    return gameRepository.findByRoomId(roomId);
  }

  @Override
  public GameData saveGameData() {
    GameData gameData = GameData.buildGameData();
    return gameRepository.save(gameData);
  }

  @Override
  public GameData playGame(String roomId, String answer) {
    GameData gameData = gameRepository.findByRoomId(roomId);
    GameHistory history = gameData.checkCount(answer);
    return saveHistory(gameData, history);
  }

  private GameData saveHistory(GameData gameData, GameHistory history) {
    return gameRepository.saveHistory(gameData, history);
  }
}
