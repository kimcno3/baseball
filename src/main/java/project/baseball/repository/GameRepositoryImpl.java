package project.baseball.repository;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import project.baseball.domain.GameData;

/**
 * Repository 구현체.
 **/

@Slf4j
@Repository
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {

  private final ConcurrentHashMap<Long, GameData> database;
  private final AtomicLong sequence;

  @Override
  public Long save(GameData gameData) {
    database.put(sequence.incrementAndGet(), gameData);
    log.info("sequence = {}", sequence);
    return sequence.get();
  }

  @Override
  public GameData findById(Long id) {
    return database.get(id);
  }
}
