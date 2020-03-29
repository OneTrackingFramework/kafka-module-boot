package de.chaintracker.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import de.chaintracker.kafka.events.UserLocated;

@Configuration
public class UserLocatedSenderConfig extends UserSenderConfig {

  @Bean
  public ProducerFactory<String, UserLocated> userLocatedProducerFactory() {
    return new DefaultKafkaProducerFactory<>(userSenderConfig());
  }


  @Bean
  public KafkaTemplate<String, UserLocated> userLocatedKafkaTemplate() {
    return new KafkaTemplate<>(userLocatedProducerFactory());
  }

}
