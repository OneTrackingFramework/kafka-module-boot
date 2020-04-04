package one.tracking.framework.kafka.config;

import one.tracking.framework.kafka.events.PushNotification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

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
}
