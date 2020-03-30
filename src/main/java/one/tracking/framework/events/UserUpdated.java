package one.tracking.framework.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdated {

  public static final String TOPIC = "users";

  private String id;

  // private String eventType = "UserUpdated";

  private String userKey;

  private String email;

  private String firstName;

  private String lastName;

  private String userName;

  private String qrCode;

  private String password;

  private String state;
}
