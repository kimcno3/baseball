package project.baseball.domain;

import java.util.ArrayList;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

/**
 * Data.
 * Entity 역할을 목적으로 생성한 클래스
 * .
 * - 필드 관련 고민사항 -
 * roomId -> 숫자만이 아닌 문자열도 포함된 Id로 변경할 경우를 고려해서 String으로 선언
 * answer -> String으로 하나의 문자열만 가지고 있는 것이 아니라 아니라 가져오는 모든 answer를 List에 저장하는 것이 좋을지
 *        -> history 구현시 필요할 지 고민
 * database가 ConcurrentHashMap이기 때문에 1차적으로 동시성 문제를 방지해준다.
 * + build 과정에서 AtomicInteger 객체를 생성해야 하는 문제가 생김
 * history -> 명확한 구현 방향을 정하지 못함, history API 구현시 구체적으로 고민해 볼 예정
  **/

@Entity
@Getter
public class GameData {
  private String roomId;
  private String answer;
  private int remainingCount;
  private int answerCount;
  private ArrayList<GameHistory> histories = new ArrayList<>();

  /**
   * .
   */

  @Builder
  public GameData(String roomId, String answer, int remainingCount, int answerCount) {
    this.roomId = roomId;
    this.answer = answer;
    this.remainingCount = remainingCount;
    this.answerCount = answerCount;
  }

  public int plusAnswerCount() {
    return this.answerCount += 1;
  }

  public int minusRemainingCount() {
    return this.remainingCount -= 1;
  }
}
