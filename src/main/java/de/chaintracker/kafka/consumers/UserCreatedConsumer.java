package de.chaintracker.kafka.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import de.chaintracker.kafka.events.UserCreated;

@Service
class UserCreatedConsumer {

  private static final Logger LOG = LoggerFactory.getLogger(UserCreatedConsumer.class);


  @KafkaListener(topics = "users",
      containerFactory = "userCreatedKafkaListenerContainerFactory")
  public void userCreatedListener(final UserCreated userCreated) {

    LOG.info("!!!user created!!!");

  }
}

