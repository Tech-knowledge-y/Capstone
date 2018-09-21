package com.capstone.tech.models;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String dayOfWeek;

    @Column
    private String timeStart;

    @Column
    private String timeEnd;

    @Column
    private String week;

    @Column
    private String year;

    // relationships

    @OneToOne
    private User user;

    // constructors/getters/setters

    public Availability() {

    }


    public Availability(String dayOfWeek, String timeStart, String timeEnd, String week, String year, User user) {
        this.dayOfWeek = dayOfWeek;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.week = week;
        this.year = year;
        this.user = user;
    }

    public Availability(long id, String dayOfWeek, String timeStart, String timeEnd, String week, String year, User user) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.week = week;
        this.year = year;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
