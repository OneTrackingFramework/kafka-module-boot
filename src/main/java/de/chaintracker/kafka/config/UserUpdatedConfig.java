package de.chaintracker.kafka.config;

import de.chaintracker.kafka.events.UserUpdated;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class UserUpdatedConfig extends KafkaConfigs {

  // ---- sender -----

  @Bean
  public ProducerFactory<String, UserUpdated> userUpdatedProducerFactory() {
    return new DefaultKafkaProducerFactory<>(senderConfigs());
  }

  @Bean
  public KafkaTemplate<String, UserUpdated> userUpdatedKafkaTemplate() {
    return new KafkaTemplate<>(userUpdatedProducerFactory());
  }


  /// ---- reciever -----
  @Bean
  @ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
  public ConsumerFactory<String, UserUpdated> userUpdatedkafkaConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        consumerConfig(),
        new StringDeserializer(),
        new JsonDeserializer<>(UserUpdated.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, UserUpdated> userUpdatedKafkaListenerContainerFactory() {

    final ConcurrentKafkaListenerContainerFactory<String, UserUpdated> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(userUpdatedkafkaConsumerFactory());
    return factory;
  }




}
