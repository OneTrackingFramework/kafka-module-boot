package de.chaintracker.kafka.producers;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import de.chaintracker.entity.ContactEvent;
import de.chaintracker.kafka.events.UserGotScanned;

@Service
public class UserGotScannedProducer extends UserProducer {

  private final KafkaTemplate<String, UserGotScanned> userProducer;

  public UserGotScannedProducer(final KafkaTemplate<String, UserGotScanned> userProducer) {
    this.userProducer = userProducer;
  }

  public void send(final ContactEvent contactEvent) {

    final var event = new UserGotScanned();
    event.userKey = contactEvent.getUser2().getId();
    event.location = "POINT (" + Math.toDegrees(contactEvent.getLocationEvent().getLongitude()) + " "
        + Math.toDegrees(contactEvent.getLocationEvent().getLatitude()) + ")";
    event.scanningUserKey = contactEvent.getUser1().getId();
    this.userProducer.send(this.topic, event);
  }
}

