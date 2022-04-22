package project.baseball.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.dtos.GameHistoriesDto;

/**
 * .
**/

@Getter
@AllArgsConstructor
public class ResponseGameHistories {
  private final boolean success;
  private final GameHistoriesDto data;
}
