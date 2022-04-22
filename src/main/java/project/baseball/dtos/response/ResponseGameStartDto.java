package project.baseball.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.dtos.GameStartDataDto;

/**
 * .
**/

@Getter
@AllArgsConstructor
public class ResponseGameStartDto {
  private boolean success;
  private GameStartDataDto data;
}
