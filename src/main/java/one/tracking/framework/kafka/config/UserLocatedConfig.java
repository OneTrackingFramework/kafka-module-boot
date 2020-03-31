package one.tracking.framework.kafka.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import one.tracking.framework.kafka.events.UserLocated;

@Configuration
@EnableKafka
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
  public ConsumerFactory<String, UserLocated> kafkaUserLocatedConsumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        consumerConfig(),
        new StringDeserializer(),
        new JsonDeserializer<>(UserLocated.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, UserLocated> userLocatedKafkaListenerContainerFactory() {

    final ConcurrentKafkaListenerContainerFactory<String, UserLocated> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(kafkaUserLocatedConsumerFactory());
    return factory;
  }


}
