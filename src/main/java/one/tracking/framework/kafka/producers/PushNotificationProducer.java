package one.tracking.framework.kafka.producers;


import one.tracking.framework.kafka.events.PushNotification;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(
        value = "app.kafka.producer.PushNotification.enabled",
        havingValue = "true")
public class PushNotificationProducer {
    private final KafkaTemplate<String, PushNotification> pushNotificationProducer;

    public PushNotificationProducer(final KafkaTemplate<String, PushNotification> pushNotificationProducer) {
        this.pushNotificationProducer = pushNotificationProducer;
    }

    public void send(final PushNotification event) {

        this.pushNotificationProducer.send(PushNotification.TOPIC, event.getUserId(), event);
    }
}
