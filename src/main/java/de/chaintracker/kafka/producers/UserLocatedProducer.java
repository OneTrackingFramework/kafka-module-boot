package de.chaintracker.kafka.producers;


import de.chaintracker.kafka.events.UserLocated;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserLocatedProducer {

    private KafkaTemplate<String, UserLocated> userProducer;

    public UserLocatedProducer(KafkaTemplate<String, UserLocated> userProducer) {
        this.userProducer = userProducer;
    }

    public void send(UserLocated event) {

        userProducer.send("usersLocated", event.userKey, event);
    }
}

