package one.tracking.framework.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDeviceMappingDeletion {
    /**
     * The Kafka topic to delete user to device mapping.
     */
    public static final String TOPIC = "delete-user-device-mapping";

    /**
     * The unique id of the user.
     */
    private String userId;
    /**
     * The unique token to delete only this device of user.
     */
    private String deviceToken;
}
