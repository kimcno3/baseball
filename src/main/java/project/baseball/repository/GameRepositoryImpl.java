package project.baseball.repository;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.baseball.domain.GameData;

/**
 * Repository 구현체.
 **/

@Repository
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {

  private final ConcurrentHashMap<AtomicLong, GameData> database = new ConcurrentHashMap<>();

}
