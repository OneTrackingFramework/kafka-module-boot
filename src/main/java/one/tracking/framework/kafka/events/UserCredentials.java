package one.tracking.framework.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {

  public static final String TOPIC = "users";

  private String userId;

  private String email;

  private String encrytedPassword;
}
