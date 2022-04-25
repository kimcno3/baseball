package project.baseball.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 랜덤 roomId 나 answer를 생성하기 위한 클래스.
 **/

public final class RandomStringMaker {

  /**
   * .
   **/

  public static final String makeAnswer() {
    String answer = "";
    while (answer.length() < 3) {
      String randomString = makeRandomString();
      if (answer.length() == 0 || !answer.contains(randomString)) {
        answer += randomString;
      }
    }
    return answer;
  }

  public static final String makeRandomString() {
    return RandomStringUtils.random(1, '1', '9', false, true);
  }

  public static final String makeRandomRoomId() {
    return RandomStringUtils.random(3, 'A', 'Z', true, false);
  }

}
