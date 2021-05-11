package com.humboshot.marnie.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15-05-2017.
 */

public class Route {
    private int Id;
    private String Name;
    private String Time;
    private Stop StopFrom = new Stop();
    private Stop StopTo = new Stop();
    private List<Stop> Stops;

    public Route() {
        Stops = new ArrayList<Stop>();
    }

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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Stop getStopFrom() {
        return StopFrom;
    }

    public void setStopFrom(Stop stopFrom) {
        StopFrom = stopFrom;
    }

    public Stop getStopTo() {
        return StopTo;
    }

    public void setStopTo(Stop stopTo) {
        StopTo = stopTo;
    }

    public List<Stop> getStops() {
        return Stops;
    }

    public void setStops(List<Stop> stops) {
        Stops = stops;
    }
}
