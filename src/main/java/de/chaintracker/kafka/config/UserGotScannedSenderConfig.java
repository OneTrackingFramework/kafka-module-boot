package de.chaintracker.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import de.chaintracker.kafka.events.UserGotScanned;

@Configuration
public class UserGotScannedSenderConfig extends UserSenderConfig {
  @Bean
  public ProducerFactory<String, UserGotScanned> userGotScannedProducerFactory() {
    return new DefaultKafkaProducerFactory<>(userSenderConfig());
  }


  @Bean
  public KafkaTemplate<String, UserGotScanned> userGotScannedKafkaTemplate() {
    return new KafkaTemplate<>(userGotScannedProducerFactory());
  }
}
