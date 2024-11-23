package com.avirantEnterprises.information_collector.model.profileupdate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserForProfileUpdate {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;

    public String getNewemail() {
        return newemail;
    }

    public void setNewemail(String newemail) {
        this.newemail = newemail;
    }

    private String newemail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
