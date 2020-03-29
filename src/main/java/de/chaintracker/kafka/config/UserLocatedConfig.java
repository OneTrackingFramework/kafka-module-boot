package de.chaintracker.kafka.config;

import de.chaintracker.kafka.events.UserLocated;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import de.chaintracker.kafka.events.UserLocated;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class UserLocatedConfig extends KafkaConfigs {

  @Bean
  public ProducerFactory<String, UserLocated> userLocatedProducerFactory() {
    return new DefaultKafkaProducerFactory<>(senderConfigs());
  }


  @Bean
  public KafkaTemplate<String, UserLocated> userLocatedKafkaTemplate() {
    return new KafkaTemplate<>(userLocatedProducerFactory());
  }



  /// ---- consumer -----
  @Bean
  @ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
  public ConsumerFactory<String, UserLocated> kafkaConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
            consumerConfig(),
            new StringDeserializer(),
            new JsonDeserializer<>(UserLocated.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, UserLocated> userLocatedKafkaListenerContainerFactory() {

    final ConcurrentKafkaListenerContainerFactory<String, UserLocated> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(kafkaConsumerFactory());
    return factory;
  }


}
