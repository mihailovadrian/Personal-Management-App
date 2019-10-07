package com.mihailov.app.management.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project Name required")
    private String projectName;

    @NotBlank(message = "Project Identifier required")
    @Size(min = 4, max = 5, message = "PLease use 4 -> 5 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    @NotBlank(message = "Description required")
    private String description;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date start_data;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date end_date;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date created_at;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date updated_at;

    public ProjectEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_data() {
        return start_data;
    }

    public void setStart_data(Date start_data) {
        this.start_data = start_data;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @PrePersist
    protected void onCreate() {
        this.created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = new Date();
    }
}
