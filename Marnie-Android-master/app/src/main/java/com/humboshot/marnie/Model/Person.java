package com.humboshot.marnie.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 15-05-2017.
 */

public class Person {
    private int Id;
    private String AuthId;
    private String Name;
    private Date Birthday;
    private String Gender;
    private String ProfilePicture;
    private List<Journey> Journeys = new ArrayList<>();
    private List<Date> Dates = new ArrayList<>();

    public Person(String auth_id)
    {
        setAuthId(auth_id);
    }

    public Person()
    {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAuthId() {
        return AuthId;
    }

    public void setAuthId(String authId) {
        AuthId = authId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getProfilePicture() {
        return ProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        ProfilePicture = profilePicture;
    }

    public List<Journey> getJourneys() {
        return Journeys;
    }

    public void setJourneys(List<Journey> journeys) {
        Journeys = journeys;
    }

    public List<Date> getDates() {
        return Dates;
    }

    public void setDates(List<Date> dates) {
        Dates = dates;
    }
}
