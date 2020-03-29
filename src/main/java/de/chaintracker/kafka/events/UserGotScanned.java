package de.chaintracker.kafka.events;


public class UserGotScanned
{

    public String eventType = "UserGotScanned";

    // WKT
    public String location;

    public String userKey;

    public String scanningUserKey;
}
