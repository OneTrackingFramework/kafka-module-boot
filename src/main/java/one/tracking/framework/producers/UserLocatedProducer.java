package one.tracking.framework.producers;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import one.tracking.framework.events.UserLocated;

@Service
@ConditionalOnProperty(
    value = "app.kafka.producer.UserLocated.enabled",
    havingValue = "true")
public class UserLocatedProducer {

  private final KafkaTemplate<String, UserLocated> userProducer;

  public UserLocatedProducer(final KafkaTemplate<String, UserLocated> userProducer) {
    this.userProducer = userProducer;
  }

  public void send(final UserLocated event) {

    this.userProducer.send("usersLocated", event.getUserKey(), event);
  }
}

