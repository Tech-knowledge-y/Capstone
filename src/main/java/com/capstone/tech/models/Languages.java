package com.capstone.tech.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "languages")
public class Languages {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String language;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languages")
    private List<UserLanguages> userLanguages;


    public Languages() {}

    public Languages(long id, String language, List<UserLanguages> userLanguages) {
        this.id = id;
        this.language = language;
        this.userLanguages = userLanguages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
