package com.avirantEnterprises.information_collector.model.personal;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String feedback;

    @ElementCollection
    @CollectionTable(name = "survey_preferences", joinColumns = @JoinColumn(name = "survey_id"))
    @Column(name = "preference")
    private List<String> preferences;

    @Column(name = "additional_docs_path")
    private String additionalDocsPath;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public String getAdditionalDocsPath() {
        return additionalDocsPath;
    }

    public void setAdditionalDocsPath(String additionalDocsPath) {
        this.additionalDocsPath = additionalDocsPath;
    }
}
