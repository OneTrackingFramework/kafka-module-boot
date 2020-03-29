package de.chaintracker.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import de.chaintracker.kafka.events.UserCreated;

@Configuration
public class UserCreatedSenderConfig extends UserSenderConfig {

  @Bean
  public ProducerFactory<String, UserCreated> userCreatedProducerFactory() {
    return new DefaultKafkaProducerFactory<>(userSenderConfig());
  }

  @Bean
  public KafkaTemplate<String, UserCreated> userCreateKafkaTemplate() {
    return new KafkaTemplate<>(userCreatedProducerFactory());
  }
}
