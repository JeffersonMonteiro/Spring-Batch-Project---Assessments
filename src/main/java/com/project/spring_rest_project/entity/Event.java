package com.project.spring_rest_project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;

    @NotBlank
    private String nameEvent;

    @NotBlank
    private  String localEvent;

    @NotBlank
    private String dateEvent;


    public long getCode() {
        return eventId;
    }

    public void setCode(long code) {
        this.eventId = code;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getLocalEvent() {
        return localEvent;
    }

    public void setLocalEvent(String localEvent) {
        this.localEvent = localEvent;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

}
