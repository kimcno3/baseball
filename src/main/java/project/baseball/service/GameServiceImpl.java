package project.baseball.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import project.baseball.domain.GameData;
import project.baseball.domain.GameHistory;
import project.baseball.domain.GameResult;
import project.baseball.repository.GameRepository;

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
  public Long save() {
    String answer = "";
    while (answer.length() < 3) {
      String randomString = RandomStringUtils.random(1, '1', '9', false, true);
      if (answer.length() == 0 || !answer.contains(randomString)) {
        answer += randomString;
      }
    }
    GameData gameData = GameData.builder()
        .roomId(RandomStringUtils.random(3, 'A', 'Z', true, false))
        .answer(answer)
        .answerCount(0)
        .remainingCount(10)
        .build();
    return gameRepository.save(gameData);
  }

  @Override
  public GameData findGameData(Long id) {
    return gameRepository.findById(id);
  }

  @Override
  public GameData findGameData(String roomId) {
    return gameRepository.findByRoomId(roomId);
  }

  /**
   * .
  **/

  @Override
  public GameResult playGame(String roomId, String answer) {
    GameData gameData = gameRepository.findByRoomId(roomId);

    // 카운트 계산
    int s = 0;
    int b = 0;
    int o = 0;

    String[] answerArray = answer.split("");
    String[] gameDataAnswerArray = gameData.getAnswer().split("");

    for (int i = 0; i < gameDataAnswerArray.length; i++) {
      for (int j = 0; j < answerArray.length; j++) {
        if (i == j && gameDataAnswerArray[i].equals(answerArray[j])) {
          // 스트라이크
          s++;
          break;
        } else if (i != j && gameDataAnswerArray[i].equals(answerArray[j])) {
          // 볼
          b++;
          break;
        } else if (j == answerArray.length - 1) {
          // 아웃
          o++;
        }
      }
    }
    // 결과값 객체에 담아서 리턴
    GameResult result = GameResult.builder()
        .strike(s)
        .ball(b)
        .out(o)
        .build();

    GameHistory history = GameHistory.builder()
        .answer(answer)
        .result(result)
        .build();
    // history 저장, 인덱스는 answer 카운터로
    gameData.getHistories().add(gameData.getAnswerCount(), history);
    // answerCount, remainingCount 수정
    gameData.plusAnswerCount();
    gameData.minusRemainingCount();

    return result;
  }
}
