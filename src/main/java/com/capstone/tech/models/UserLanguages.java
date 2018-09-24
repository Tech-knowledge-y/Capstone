package com.capstone.tech.models;

import javax.persistence.*;

@Entity
@Table(name = "user_languages")
public class UserLanguages {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Languages languages;

    public UserLanguages() {}

    public UserLanguages(long id, User user, Languages languages) {
        this.id = id;
        this.user = user;
        this.languages = languages;
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

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }
}
