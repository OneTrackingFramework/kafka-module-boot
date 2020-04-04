package one.tracking.framework.kafka.config;

import one.tracking.framework.kafka.events.UserDeviceMapping;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;


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

    @Bean(name = "kafka.consumer.UserDeviceMapping")
    public ConsumerFactory<String, UserDeviceMapping> userDeviceMappingFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfig(),
                new StringDeserializer(),
                new JsonDeserializer<>(UserDeviceMapping.class));
    }

    @Bean(name = "kafka.listener.UserDeviceMapping")
    public ConcurrentKafkaListenerContainerFactory<String, UserDeviceMapping> listenerContainerFactory() {

        final ConcurrentKafkaListenerContainerFactory<String, UserDeviceMapping> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userDeviceMappingFactory());
        return factory;
    }
}
