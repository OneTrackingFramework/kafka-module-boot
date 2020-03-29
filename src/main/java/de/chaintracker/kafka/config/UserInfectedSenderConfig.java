package de.chaintracker.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import de.chaintracker.kafka.events.UserInfected;

@Configuration
public class UserInfectedSenderConfig extends UserSenderConfig {

  @Bean
  public ProducerFactory<String, UserInfected> userInfectedProducerFactory() {
    return new DefaultKafkaProducerFactory<>(userSenderConfig());
  }


  @Bean
  public KafkaTemplate<String, UserInfected> userInfectedScannedKafkaTemplate() {
    return new KafkaTemplate<>(userInfectedProducerFactory());
  }

}
