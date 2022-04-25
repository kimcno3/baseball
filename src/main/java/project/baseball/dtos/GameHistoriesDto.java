package project.baseball.dtos;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import project.baseball.domain.GameHistory;

/**
 *.
 */

@Getter
@AllArgsConstructor
public class GameHistoriesDto {
  private final Collection<GameHistory> histories;
}
