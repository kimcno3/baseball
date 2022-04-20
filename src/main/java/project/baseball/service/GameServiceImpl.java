package project.baseball.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.baseball.repository.GameRepository;

/**
 * Service 구현체.
 **/

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

  private final GameRepository gameRepository;

  @Override
  public String saveRoomId() {

    return "456";
  }
}
