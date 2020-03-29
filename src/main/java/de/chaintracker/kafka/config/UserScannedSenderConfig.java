package de.chaintracker.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import de.chaintracker.kafka.events.UserScanned;

@Configuration
public class UserScannedSenderConfig extends UserSenderConfig {
  @Bean
  public ProducerFactory<String, UserScanned> userScannedProducerFactory() {
    return new DefaultKafkaProducerFactory<>(userSenderConfig());
  }


  @Bean
  public KafkaTemplate<String, UserScanned> userScannedKafkaTemplate() {
    return new KafkaTemplate<>(userScannedProducerFactory());
  }

}
