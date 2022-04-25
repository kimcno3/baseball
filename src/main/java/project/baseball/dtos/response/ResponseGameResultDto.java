package project.baseball.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.dtos.GameResultDto;

/**
 *.
 */

@Getter
@AllArgsConstructor
public class ResponseGameResultDto {
  private final boolean success;
  private final GameResultDto data;
}
