package one.tracking.framework.kafka.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDeviceMapping {
    /**
     * The Kafka topic to create user to device mapping.
     */
    public static final String TOPIC = "create-user-device-mapping";
    /**
     * The unique id of the user.
     */
    private String userId;
    /**
     * The device token / id to send the push notification to.
     */
    private String deviceToken;
}
