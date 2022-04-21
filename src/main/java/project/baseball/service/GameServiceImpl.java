package project.baseball.service;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import project.baseball.domain.GameData;
import project.baseball.repository.GameRepository;

/**
 * Service 구현체.
 *
 **/

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

  private final GameRepository gameRepository;

  @Override
  public Long save() {
    GameData gameData = GameData.builder()
        .correct(true)
        .roomId(RandomStringUtils.random(3, '1', '9', false, true))
        .answer("")
        .answerCount(0)
        .remainingCount(10)
        .strike(0)
        .ball(0)
        .out(0)
        .histories(new ArrayList<>())
        .build();

    return gameRepository.save(gameData);
  }

  public GameData findGameData(Long id) {
    return gameRepository.findById(id);
  }
}
