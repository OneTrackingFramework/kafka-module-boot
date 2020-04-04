package one.tracking.framework.kafka.config;

import one.tracking.framework.kafka.events.UserDeviceMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


@Configuration
@EnableKafka
public class UserDeviceMappingConfig extends KafkaConfigs {
    @Bean(name = "kafka.producer.UserDeviceMapping")
    public ProducerFactory<String, UserDeviceMapping> userDeviceMappingProducerFactory() {
        return new DefaultKafkaProducerFactory<>(senderConfigs());
    }

    @Bean(name = "kafka.template.UserDeviceMapping")
    public KafkaTemplate<String, UserDeviceMapping> userDeviceMappingKafkaTemplate() {
        return new KafkaTemplate<>(userDeviceMappingProducerFactory());
    }
}
