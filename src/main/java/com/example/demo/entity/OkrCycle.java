package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "okr_cycles")
public class OkrCycle {

    public enum CycleType {
        QUARTERLY,
        ANNUAL
    }

    public enum CycleStatus {
        DRAFT,
        ACTIVE,
        CLOSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false, length=200)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name="cycle_type", nullable=false)
    private CycleType cycleType;

    @Column(name="start_date", nullable=false)
    private LocalDate startDate;

    @Column(name="end_date", nullable=false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    private CycleStatus status = CycleStatus.DRAFT;

    @Column(name="created_by", nullable=false)
    private Long createdBy;

    @Column(name="created_at", updatable=false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (status == null) status = CycleStatus.DRAFT;
    }

    public OkrCycle() {
    }

    public OkrCycle(Long id, String title, CycleType cycleType, LocalDate startDate, LocalDate endDate,
            CycleStatus status, Long createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.cycleType = cycleType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public CycleType getCycleType() {
        return cycleType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public CycleStatus getStatus() {
        return status;
    }

    public Long getCreatedBy() {
        return createdBy;
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

    public void setCycleType(CycleType cycleType) {
        this.cycleType = cycleType;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStatus(CycleStatus status) {
        this.status = status;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
}