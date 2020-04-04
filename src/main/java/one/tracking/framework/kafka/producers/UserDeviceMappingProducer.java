package one.tracking.framework.kafka.producers;


import one.tracking.framework.kafka.events.UserDeviceMapping;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
        value = "app.kafka.producer.UserDeviceMapping.enabled",
        havingValue = "true")
public class UserDeviceMappingProducer {
    private final KafkaTemplate<String, UserDeviceMapping> userDeviceMappingProducer;

    public UserDeviceMappingProducer(final KafkaTemplate<String, UserDeviceMapping> userDeviceMappingProducer) {
        this.userDeviceMappingProducer = userDeviceMappingProducer;
    }

    public void send(final UserDeviceMapping event) {

        this.userDeviceMappingProducer.send(UserDeviceMapping.TOPIC, event.getUserId(), event);
    }
}
