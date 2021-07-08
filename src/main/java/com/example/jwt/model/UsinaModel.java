package com.example.jwt.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Data
@Entity(name="usina_model")
public class UsinaModel {




    @Id
    @Column(unique = true)
    private String hash;
    private String status = "off";
    private String people;

    public UsinaModel() { }

    public UsinaModel(String people, String hash) {
        this.people = people;
        this.hash = hash;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
