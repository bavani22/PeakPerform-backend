package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "progress_snapshots")
public class ProgressSnapshot {
                                                   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="objective_id", nullable=false)
    private Long objectiveId;

    @Column(name="snapshot_date", nullable=false)
    private LocalDate snapshotDate;

    @Column(name="progress_percent", nullable=false)
    private Double progressPercent;

    @Column(name="captured_by", nullable=false)
    private Long capturedBy;

    public ProgressSnapshot() {
    }

    public ProgressSnapshot( Long objectiveId, LocalDate snapshotDate, Double progressPercent,
            Long capturedBy) {
        

        this.objectiveId = objectiveId;
        this.snapshotDate = snapshotDate;
        this.progressPercent = progressPercent;
        this.capturedBy = capturedBy;
    }

    public Long getId() {
        return id;
    }

    public Long getObjectiveId() {
        return objectiveId;
    }

    public LocalDate getSnapshotDate() {
        return snapshotDate;
    }

    public Double getProgressPercent() {
        return progressPercent;
    }

    public Long getCapturedBy() {
        return capturedBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public void setSnapshotDate(LocalDate snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public void setProgressPercent(Double progressPercent) {
        this.progressPercent = progressPercent;
    }

    public void setCapturedBy(Long capturedBy) {
        this.capturedBy = capturedBy;
    }
}