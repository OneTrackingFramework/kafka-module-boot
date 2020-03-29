package de.chaintracker.kafka.producers;


import de.chaintracker.kafka.events.UserUpdated;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {

  private final KafkaTemplate<String, UserUpdated> userUpdatedProducer;

  public UserProducer(final KafkaTemplate<String, UserUpdated> userUpdatedProducer) {
    this.userUpdatedProducer = userUpdatedProducer;
  }


  public void userUpdated(UserUpdated userUpdated) {
    this.userUpdatedProducer.send("usersLocated", userUpdated.id, userUpdated);
  }

}

