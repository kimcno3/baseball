package project.baseball.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * RequestAnswerDto.
 * 플레이어로부터 정답을 입력 받으면 그 값을 객체로 파싱하기 위한 목적의 Dto
 **/

@Getter
@NoArgsConstructor
public class RequestAnswerDto {
  private String answer;
}
