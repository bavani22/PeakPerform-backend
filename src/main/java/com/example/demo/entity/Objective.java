package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "objectives")
public class Objective {

    public enum ObjectiveStatus {
        DRAFT,
        ACTIVE,
        PAUSED,
        COMPLETED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false, length=300)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name="owner_id", nullable=false)
    private Long ownerId;

    @Column(name="cycle_id", nullable=false)
    private Long cycleId;

    @Column(name="team_lead_id")
    private Long teamLeadId;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    private ObjectiveStatus status = ObjectiveStatus.DRAFT;

    @Column(name="progress_percent", nullable=false)
    private Double progressPercent = 0.0;

    @Column(name="created_at", updatable=false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (progressPercent == null) progressPercent = 0.0;
        if (status == null) status = ObjectiveStatus.DRAFT;
    }

    public Objective() {
    }

    public Objective(Long id, String title, String description, Long ownerId, Long cycleId, Long teamLeadId,
            ObjectiveStatus status, Double progressPercent, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
        this.cycleId = cycleId;
        this.teamLeadId = teamLeadId;
        this.status = status;
        this.progressPercent = progressPercent;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Long getCycleId() {
        return cycleId;
    }

    public Long getTeamLeadId() {
        return teamLeadId;
    }

    public ObjectiveStatus getStatus() {
        return status;
    }

    public Double getProgressPercent() {
        return progressPercent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setCycleId(Long cycleId) {
        this.cycleId = cycleId;
    }

    public void setTeamLeadId(Long teamLeadId) {
        this.teamLeadId = teamLeadId;
    }

    public void setStatus(ObjectiveStatus status) {
        this.status = status;
    }

    public void setProgressPercent(Double progressPercent) {
        this.progressPercent = progressPercent;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
}