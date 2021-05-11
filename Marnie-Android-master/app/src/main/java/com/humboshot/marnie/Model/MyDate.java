package com.humboshot.marnie.Model;
import java.util.Date;

/**
 * Created by User on 15-05-2017.
 */

public class MyDate {
    private int Id;
    private int RouteId;
    private String DateStartLocation;
    private Date StartTime;
    private String DateDestination;
    private Date EndTime;
    private int Person1Id;
    private Person Person1;
    private int Person2Id;
    private Person Person2;
    private int StatusP1;
    private int StatusP2;
    private int DateStatus;
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

    public String getDateStartLocation() {
        return DateStartLocation;
    }

    public void setDateStartLocation(String dateStartLocation) {
        DateStartLocation = dateStartLocation;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public String getDateDestination() {
        return DateDestination;
    }

    public void setDateDestination(String dateDestination) {
        DateDestination = dateDestination;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public int getPerson1Id() {
        return Person1Id;
    }

    public void setPerson1Id(int person1Id) {
        Person1Id = person1Id;
    }

    public Person getPerson1() {
        return Person1;
    }

    public void setPerson1(Person person1) {
        Person1 = person1;
    }

    public int getPerson2Id() {
        return Person2Id;
    }

    public void setPerson2Id(int person2Id) {
        Person2Id = person2Id;
    }

    public Person getPerson2() {
        return Person2;
    }

    public void setPerson2(Person person2) {
        Person2 = person2;
    }

    public int getStatusP1() {
        return StatusP1;
    }

    public void setStatusP1(int statusP1) {
        StatusP1 = statusP1;
    }

    public int getStatusP2() {
        return StatusP2;
    }

    public void setStatusP2(int statusP2) {
        StatusP2 = statusP2;
    }

    public int getDateStatus() {
        return DateStatus;
    }

    public void setDateStatus(int dateStatus) {
        DateStatus = dateStatus;
    }

    public com.humboshot.marnie.Model.Route getRoute() {
        return Route;
    }

    public void setRoute(com.humboshot.marnie.Model.Route route) {
        Route = route;
    }
}
