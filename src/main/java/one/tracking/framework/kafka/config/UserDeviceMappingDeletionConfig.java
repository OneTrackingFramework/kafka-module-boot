package one.tracking.framework.kafka.config;

import one.tracking.framework.kafka.events.UserDeviceMappingDeletion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


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
}
