package com.humboshot.marnie.Model;

/**
 * Created by User on 15-05-2017.
 */

public class Stop {
   private int Id;
   private int RouteId;
   private int StationId;
   private String ArrivalTime;
   private String DepartureTime;
   private Route Route;
   private Station Station;

    public Stop() {
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }

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

    public int getStationId() {
        return StationId;
    }

    public void setStationId(int stationId) {
        StationId = stationId;
    }

    public com.humboshot.marnie.Model.Route getRoute() {
        return Route;
    }

    public void setRoute(com.humboshot.marnie.Model.Route route) {
        Route = route;
    }

    public com.humboshot.marnie.Model.Station getStation() {
        return Station;
    }

    public void setStation(com.humboshot.marnie.Model.Station station) {
        Station = station;
    }
}
