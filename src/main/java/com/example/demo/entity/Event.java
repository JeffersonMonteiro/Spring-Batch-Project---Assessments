package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "tbEvents")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_event;
    @Enumerated(EnumType.STRING)
    private ActivityType type;
    private String dateCode;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id_volunt", nullable = false)
    private Volunteer volunteer;


    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public String getDateCode() {
        return dateCode;
    }

    public void setDateCode(String dateCode) {
        this.dateCode = dateCode;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
}
