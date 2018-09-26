package com.capstone.tech.models;

import javax.persistence.*;

@Entity
@Table(name = "user_availability")
public class UserAvailability {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "availability_id")
    private Availability availability;

    public UserAvailability() {}

    public UserAvailability(long id, User user, Availability availability) {
        this.id = id;
        this.user = user;
        this.availability = availability;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
