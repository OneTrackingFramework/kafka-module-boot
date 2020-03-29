package de.chaintracker.kafka.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import de.chaintracker.kafka.events.UserCreated;

@Configuration
@EnableKafka
public class UserCreatedConsumerConfig extends UserConsumerConfig {

  @Bean
  public ConsumerFactory<String, UserCreated> kafkaConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        userConsumerConfig(),
        new StringDeserializer(),
        new JsonDeserializer<>(UserCreated.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, UserCreated> userCreatedKafkaListenerContainerFactory() {

    final ConcurrentKafkaListenerContainerFactory<String, UserCreated> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(kafkaConsumerFactory());
    return factory;
  }
}
