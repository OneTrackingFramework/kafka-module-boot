package one.tracking.framework.kafka.config;

import one.tracking.framework.kafka.events.PushNotification;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class PushNotificationConfig extends KafkaConfigs {
    @Bean(name = "kafka.producer.PushNotification")
    public ProducerFactory<String, PushNotification> pushNotificationProducerFactory() {
        return new DefaultKafkaProducerFactory<>(senderConfigs());
    }

    @Bean(name = "kafka.template.PushNotification")
    public KafkaTemplate<String, PushNotification> pushNotificationKafkaTemplate() {
        return new KafkaTemplate<>(pushNotificationProducerFactory());
    }

    @Bean(name = "kafka.consumer.PushNotification")
    public ConsumerFactory<String, PushNotification> pushNotificationFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                new JsonDeserializer<>(PushNotification.class));
    }

    @Bean(name = "kafka.listener.PushNotification")
    public ConcurrentKafkaListenerContainerFactory<String, PushNotification> listenerContainerFactory() {

        final ConcurrentKafkaListenerContainerFactory<String, PushNotification> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(pushNotificationFactory());
        return factory;
    }
}
