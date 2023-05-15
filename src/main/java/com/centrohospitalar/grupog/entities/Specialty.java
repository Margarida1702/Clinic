package com.centrohospitalar.grupog.entities;

import com.centrohospitalar.grupog.models.Slot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.sql.Time;


@Entity
public class Specialty {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String name;

    private String imageName;
    private File file;
    @Column(length = 1500)
    private String description;

    public static final int SPECIALTY_APPOINTMENT_MINUTES_DUR = 60;

    public Specialty() {

    }

    public Specialty(String name, String description, String imageName) {
        this.name = name;
        this.description = description;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }


    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Specialty other = (Specialty) obj;
        if (!this.getName().equals(other.getName())){
            return false;
        }

        return true;
    }
}
