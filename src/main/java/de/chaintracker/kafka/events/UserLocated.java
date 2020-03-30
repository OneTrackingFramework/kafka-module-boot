package de.chaintracker.kafka.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLocated {

  private String location;

  private String userKey;
}
