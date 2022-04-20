package project.baseball.domain;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import lombok.Setter;

/**
 * Data.
 * Entity 역할을 목적으로 생성한 클래스
 * Getter, Setter 만으로 1차적 기능 구현 예정(Build 미사용)
 * .
 * - 필드 관련 고민사항 -
 * roomId -> 숫자만이 아닌 문자열도 포함된 Id로 변경할 경우를 고려해서 String으로 선언
 * AtomicInteger 선택 -> 동시성 이슈를 고려, 하지만 id를 부여받기 때문에 고민을 하지 않아도 될지 모르겠다...
 * answer -> String으로 하나의 문자열만 가지고 있는 것이 아니라 아니라 가져오는 모든 answer를 List에 저장하는 것이 좋을지
 *        -> history 구현시 필요할 지 고민
 * history -> 명확한 구현 방향을 정하지 못함, history API 구현시 구체적으로 고민해 볼 예정
  **/

@Getter
@Setter
public class GameData {
  private boolean correct;
  private String roomId;
  private String answer;
  private AtomicInteger remainingCount;
  private AtomicInteger answerCount;
  private AtomicInteger strike;
  private AtomicInteger ball;
  private AtomicInteger out;
  private ArrayList<GameHistory> histories;
}
