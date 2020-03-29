package de.chaintracker.kafka.events;


public class UserLocated
{

    public String eventType = "UserLocated";

    // WKT
    public String location;

    public String userKey;
}
