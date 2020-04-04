package one.tracking.framework.kafka.producers;


import one.tracking.framework.kafka.events.UserDeviceMappingDeletion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
        value = "app.kafka.producer.UserDeviceMappingDeletion.enabled",
        havingValue = "true")
public class UserDeviceMappingDeletionProducer {
    private final KafkaTemplate<String, UserDeviceMappingDeletion> userDeviceMappingDeletionProducer;

    public UserDeviceMappingDeletionProducer(final KafkaTemplate<String, UserDeviceMappingDeletion> userDeviceMappingDeletionProducer) {
        this.userDeviceMappingDeletionProducer = userDeviceMappingDeletionProducer;
    }

    public void send(final UserDeviceMappingDeletion event) {

        this.userDeviceMappingDeletionProducer.send(UserDeviceMappingDeletion.TOPIC, event.getUserId(), event);
    }
}
