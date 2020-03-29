package de.chaintracker.kafka.producers;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import de.chaintracker.entity.User;
import de.chaintracker.kafka.events.UserCreated;

@Service
public class UserCreatedProducer extends UserProducer {

  private final KafkaTemplate<String, UserCreated> userProducer;

  public UserCreatedProducer(final KafkaTemplate<String, UserCreated> userProducer) {
    this.userProducer = userProducer;
  }

  public void send(final User user) {

    final var event = new UserCreated();
    event.email = user.getEmail();
    event.firstName = user.getFirstName();
    event.lastName = user.getLastName();
    event.qrCode = user.getQrCode();
    event.userName = user.getUserName();
    event.userKey = user.getId();
    this.userProducer.send(this.topic, event);
  }
}

