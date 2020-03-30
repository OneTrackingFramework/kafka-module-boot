package de.chaintracker.kafka.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
