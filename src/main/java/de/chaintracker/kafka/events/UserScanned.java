package de.chaintracker.kafka.events;


public class UserScanned
{

    public String eventType = "UserScanned";

    // WKT
    public String location;

    public String userKey;

    public String scannedUserKey;
}
