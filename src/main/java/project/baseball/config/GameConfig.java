package project.baseball.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import project.baseball.domain.GameData;

/**
 * .
 **/

@Configuration
@ComponentScan(basePackages = "project.baseball")
public class GameConfig {

  @Bean
  public ConcurrentHashMap<Long, GameData> database() {
    return new ConcurrentHashMap<>();
  }

  @Bean
  public AtomicLong sequence() {
    return new AtomicLong(0L);
  }
}
