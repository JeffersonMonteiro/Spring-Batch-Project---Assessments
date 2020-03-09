package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "tbVolunteers")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id_volunt")
    private int id_volunt;
    private String name;
    private int amntBuilding;
    private int amntSurvey;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "volunteer")
    public List<Event> eventsList;

    public int getId_volunt() {
        return id_volunt;
    }

    public void setId_volunt(int id_volunt) {
        this.id_volunt = id_volunt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmntBuilding() {
        return amntBuilding;
    }

    public void setAmntBuilding(int amntBuilding) {
        this.amntBuilding = amntBuilding;
    }

    public int getAmntSurvey() {
        return amntSurvey;
    }

    public void setAmntSurvey(int amntSurvey) {
        this.amntSurvey = amntSurvey;
    }

    public List<Event> getEvents() {
        return eventsList;
    }

    public void setEvents(List<Event> events) {
        this.eventsList = events;
    }

    public List<Event> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
    }

}
