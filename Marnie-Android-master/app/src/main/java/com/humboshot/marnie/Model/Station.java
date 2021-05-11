package com.humboshot.marnie.Model;

/**
 * Created by Martin on 15-05-2017.
 * Describes a Station, and its location.
 */

public class Station {
    private int Id;
    private String Name;
    private double Latitude;
    private double Longitude;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
