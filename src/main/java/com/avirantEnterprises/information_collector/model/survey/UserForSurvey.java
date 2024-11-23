package com.avirantEnterprises.information_collector.model.survey;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserForSurvey {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public String getSatisfactionlevel() {
        return satisfactionlevel;
    }

    public void setSatisfactionlevel(String satisfactionlevel) {
        this.satisfactionlevel = satisfactionlevel;
    }

    private String satisfactionlevel;
    private String feedback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
