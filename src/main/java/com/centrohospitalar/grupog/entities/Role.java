package com.centrohospitalar.grupog.entities;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

