package one.tracking.framework.kafka.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import one.tracking.framework.kafka.events.UserUpdated;

@Service
@ConditionalOnProperty(
    value = "app.kafka.consumer.UserUpdated.enabled",
    havingValue = "true")
public class UserUpdatedConsumer {

  private static final Logger LOG = LoggerFactory.getLogger(UserUpdatedConsumer.class);

  @KafkaListener(topics = UserUpdated.TOPIC,
      containerFactory = "userUpdatedKafkaListenerContainerFactory")
  public void userUpdatedListener(final UserUpdated userUpdated) {
    LOG.info("!!!user " + userUpdated.getId() + " updated!!!");
  }
}
