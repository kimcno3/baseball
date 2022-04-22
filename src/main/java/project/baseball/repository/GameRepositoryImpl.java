package project.baseball.repository;


import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;

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
  public void saveHistory(String roomId, GameHistory history) {
    GameData gameData = findByRoomId(roomId);

    ArrayList<GameHistory> histories = gameData.getHistories();
    histories.add(gameData.getAnswerCount(), history);

    gameData.plusAnswerCount();
    gameData.minusRemainingCount();
  }

  @Override
  public GameData findById(Long id) {
    return database.get(id);
  }

  @Override
  public GameData findByRoomId(String roomId) {
    for (GameData tempData : database.values()) {
      if (tempData.getRoomId().equals(roomId)) {
        return tempData;
      }
    }
    throw new NullPointerException("해당 roomId는 없습니다");
  }
}
