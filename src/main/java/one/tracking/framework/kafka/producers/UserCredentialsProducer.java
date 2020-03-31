package one.tracking.framework.kafka.producers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import one.tracking.framework.kafka.events.UserCredentials;

@Service
@ConditionalOnProperty(
    value = "app.kafka.producer.UserCredentials.enabled", // TODO
    havingValue = "true")
public class UserCredentialsProducer {

  private final KafkaTemplate<String, String> producer;

  public UserCredentialsProducer(final KafkaTemplate<String, String> producer) {
    this.producer = producer;
  }

  public void send(final String userId, final String event) {
    this.producer.send(UserCredentials.TOPIC, userId, event);
  }
}
