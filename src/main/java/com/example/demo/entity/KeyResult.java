package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "key_results")
public class KeyResult {

    public enum MetricType {
        NUMERIC,
        PERCENTAGE,
        BOOLEAN
    }

    public enum KeyResultStatus {
        ON_TRACK,
        AT_RISK,
        BEHIND,
        COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable=false, length=300)
    private String title;

    @Column(name="objective_id", nullable=false)
    private Long objectiveId;

    @Enumerated(EnumType.STRING)
    @Column(name="metric_type", nullable=false, length=20)
    private MetricType metricType;

    @Column(name="target_value", nullable=false)
    private Double targetValue;

    @Column(name="current_value", nullable=false)
    private Double currentValue = 0.0;

    @Column(name="unit", length=50)
    private String unit;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false, length=20)
    private KeyResultStatus status = KeyResultStatus.ON_TRACK;

    @Column(name="due_date")
    private LocalDate dueDate;

    public KeyResult() {}

    @PrePersist
    public void prePersist() {
        if (currentValue == null) currentValue = 0.0;
        if (status == null) status = KeyResultStatus.ON_TRACK;
    }

    public KeyResult(Long id, String title, Long objectiveId, MetricType metricType, Double targetValue,
            Double currentValue, String unit, KeyResultStatus status, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.objectiveId = objectiveId;
        this.metricType = metricType;
        this.targetValue = targetValue;
        this.currentValue = currentValue;
        this.unit = unit;
        this.status = status;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getObjectiveId() {
        return objectiveId;
    }

    public MetricType getMetricType() {
        return metricType;
    }

    public Double getTargetValue() {
        return targetValue;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public String getUnit() {
        return unit;
    }

    public KeyResultStatus getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public void setTargetValue(Double targetValue) {
        this.targetValue = targetValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setStatus(KeyResultStatus status) {
        this.status = status;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    
    

}