package one.tracking.framework.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushNotification {
    /**
     * The Kafka topic to send push notifications.
     */
    public static final String TOPIC = "push_notification";

    /**
     * The unique id of the user.
     */
    private String userId;
    /**
     * The title of the message.
     */
    private String title;
    /**
     * The body of the message.
     */
    private String body;
}
