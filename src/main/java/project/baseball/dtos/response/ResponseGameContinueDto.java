package project.baseball.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.dtos.GameAnswerDataDto;

/**
 *.
 */

@Getter
@AllArgsConstructor
public class ResponseGameContinueDto {
  private boolean success;
  private GameAnswerDataDto data;
}
