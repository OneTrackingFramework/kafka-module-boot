package de.chaintracker.kafka.producers;


import de.chaintracker.entity.ContactEvent;
import de.chaintracker.entity.Infection;
import de.chaintracker.kafka.events.UserInfected;
import de.chaintracker.kafka.events.UserScanned;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserInfectedProducer extends UserProducer {

    private KafkaTemplate<String, UserInfected> userProducer;

    public UserInfectedProducer(KafkaTemplate<String, UserInfected> userProducer) {
        this.userProducer = userProducer;
    }
    public void send(Infection infection) {

        var event = new UserInfected();
        event.userKey = infection.getUser().getId();
        userProducer.send(topic, event);
    }
}

