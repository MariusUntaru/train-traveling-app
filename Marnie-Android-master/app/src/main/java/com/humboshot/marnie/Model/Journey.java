package com.humboshot.marnie.Model;

import java.util.Date;

/**
 * Created by User on 15-05-2017.
 */

public class Journey {  
    private int Id;
    private int RouteId;
    private int PersonId;
    private String StartLocation;
    private Date StartTime;
    private String Destination;
    private Date EndTime;
    private int Status;
    private Person Person;
    private Route Route;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRouteId() {
        return RouteId;
    }

    public void setRouteId(int routeId) {
        RouteId = routeId;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    public String getStartLocation() {
        return StartLocation;
    }

    public void setStartLocation(String startLocation) {
        StartLocation = startLocation;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public com.humboshot.marnie.Model.Person getPerson() {
        return Person;
    }

    public void setPerson(com.humboshot.marnie.Model.Person person) {
        Person = person;
    }

    public Route getRoute() {
        return Route;
    }

    public void setRoute(Route route) {
        Route = route;
    }
}
