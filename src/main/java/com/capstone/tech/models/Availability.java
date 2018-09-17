package com.capstone.tech.models;

import javax.persistence.*;
import java.sql.Time;


@Entity
@Table(name = "availability")
public class Availability {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String day_of_week;

    @Column(nullable = false)
    private int week;

    @Column(length = 4, nullable = false)
    private String year;

    @Column(nullable = false)
    private Time time_start;

    @Column(nullable = false)
    private Time time_end;

    // relationships

    @OneToOne
    private User user;

    // constructors/getters/setters

    private Availability() { }

    public Availability(String day_of_week, int week, String year, Time time_start, Time time_end, User user) {
        this.day_of_week = day_of_week;
        this.week = week;
        this.year = year;
        this.time_start = time_start;
        this.time_end = time_end;
        this.user = user;
    }

    public Availability(int id, String day_of_week, int week, String year, Time time_start, Time time_end, User user) {
        this.id = id;
        this.day_of_week = day_of_week;
        this.week = week;
        this.year = year;
        this.time_start = time_start;
        this.time_end = time_end;
        this.user = user;
    }

    private long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    private String getDay_of_week() {
        return day_of_week;
    }

    private void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    private int getWeek() {
        return week;
    }

    private void setWeek(int week) {
        this.week = week;
    }

    private String getYear() {
        return year;
    }

    private void setYear(String year) {
        this.year = year;
    }

    private Time getTime_start() {
        return time_start;
    }

    private void setTime_start(Time time_start) {
        this.time_start = time_start;
    }

    private Time getTime_end() {
        return time_end;
    }

    private void setTime_end(Time time_end) {
        this.time_end = time_end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
