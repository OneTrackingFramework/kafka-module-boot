package one.tracking.framework.kafka.producers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import one.tracking.framework.kafka.events.UserUpdated;

@Service
@ConditionalOnProperty(
    value = "app.kafka.producer.UserUpdated.enabled",
    havingValue = "true")
public class UserUpdatedProducer {

  private final KafkaTemplate<String, UserUpdated> userUpdatedProducer;

  public UserUpdatedProducer(final KafkaTemplate<String, UserUpdated> userUpdatedProducer) {
    this.userUpdatedProducer = userUpdatedProducer;
  }

  public void userUpdated(final UserUpdated userUpdated) {
    this.userUpdatedProducer.send(UserUpdated.TOPIC, userUpdated.getId(), userUpdated);
  }

}
