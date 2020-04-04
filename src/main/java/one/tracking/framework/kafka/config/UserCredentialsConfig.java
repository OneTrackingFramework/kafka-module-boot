package one.tracking.framework.kafka.config;

import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
import one.tracking.framework.kafka.consumer.handler.IUserCredentialsEventHandler;

@Configuration
@EnableKafka
public class UserCredentialsConfig extends KafkaConfigs {

  @Bean(name = "kafka.producer.UserCredentials")
  public ProducerFactory<String, String> userCredentialsProducerFactory() {
    return new DefaultKafkaProducerFactory<>(senderConfigs());
  }

  @Bean(name = "kafka.template.UserCredentials")
  public KafkaTemplate<String, String> userCredentialsKafkaTemplate() {
    return new KafkaTemplate<>(userCredentialsProducerFactory());
  }

  @Bean(name = "kafka.consumer.UserCredentials")
  @ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
  @ConditionalOnBean(IUserCredentialsEventHandler.class)
  public ConsumerFactory<String, String> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(
        consumerConfig(),
        new StringDeserializer(),
        new StringDeserializer());
  }

  @Bean(name = "kafka.listener.UserCredentials")
  @ConditionalOnBean(IUserCredentialsEventHandler.class)
  public ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory() {

    final ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  @Override
  public Map<String, Object> senderConfigs() {
    final Map<String, Object> props = super.senderConfigs();
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    return props;
  }
}
