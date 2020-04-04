package one.tracking.framework.kafka.config;

import one.tracking.framework.kafka.events.UserDeviceMappingDeletion;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;


@Configuration
@EnableKafka
public class UserDeviceMappingDeletionConfig extends KafkaConfigs {
    @Bean(name = "kafka.producer.UserDeviceMappingDeletion")
    public ProducerFactory<String, UserDeviceMappingDeletion> userDeviceMappingDeletionProducerFactory() {
        return new DefaultKafkaProducerFactory<>(senderConfigs());
    }

    @Bean(name = "kafka.template.UserDeviceMappingDeletion")
    public KafkaTemplate<String, UserDeviceMappingDeletion> userDeviceMappingDeletionKafkaTemplate() {
        return new KafkaTemplate<>(userDeviceMappingDeletionProducerFactory());
    }

    @Bean(name = "kafka.consumer.UserDeviceMappingDeletion")
    public ConsumerFactory<String, UserDeviceMappingDeletion> userDeviceMappingDeletionFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                new JsonDeserializer<>(UserDeviceMappingDeletion.class));
    }

    @Bean(name = "kafka.listener.UserDeviceMappingDeletion")
    public ConcurrentKafkaListenerContainerFactory<String, UserDeviceMappingDeletion> listenerContainerFactory() {

        final ConcurrentKafkaListenerContainerFactory<String, UserDeviceMappingDeletion> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userDeviceMappingDeletionFactory());
        return factory;
    }
}
