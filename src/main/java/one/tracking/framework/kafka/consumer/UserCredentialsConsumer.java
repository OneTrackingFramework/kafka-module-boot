package one.tracking.framework.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import one.tracking.framework.kafka.consumer.handler.IUserCredentialsEventHandler;
import one.tracking.framework.kafka.events.UserCredentials;

@Service
@ConditionalOnBean(IUserCredentialsEventHandler.class)
public class UserCredentialsConsumer {

  @Autowired
  private IUserCredentialsEventHandler eventHandler;

  private static final Logger LOG = LoggerFactory.getLogger(UserCredentialsConsumer.class);

  @KafkaListener(topics = UserCredentials.TOPIC, containerFactory = "kafka.listener.UserCredentials")
  public void consume(final String event) throws Exception {
    LOG.debug("Received KAFKA event: {}", event);

    this.eventHandler.consume(event);
  }
}
