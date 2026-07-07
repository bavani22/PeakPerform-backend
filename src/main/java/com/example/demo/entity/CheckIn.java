package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "check_ins")
public class CheckIn {

    public enum ReviewStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="key_result_id",nullable=false)
    private Long keyResultId;

    @Column(name="reported_value",nullable=false)
    private Double reportedValue;

    @Column(name="confidence_level",nullable=false)
    private Integer confidenceLevel;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name="submitted_by",nullable=false)
    private Long submittedByUserId;

    @Column(name="submitted_at",updatable=false)
    private LocalDateTime submittedAt;

    @Enumerated(EnumType.STRING)
    @Column(name="review_status",nullable=false)
    private ReviewStatus reviewStatus = ReviewStatus.PENDING;

    @Column(name="reviewed_by")
    private Long reviewedByUserId;

    @Column(name="reviewed_at")
    private LocalDateTime reviewedAt;

    @Column(name="rejection_reason",columnDefinition="TEXT")
    private String rejectionReason;

    @PrePersist
    public void onCreate() {
        this.submittedAt = LocalDateTime.now();
    }

    public CheckIn() {
    }

    public CheckIn(Long id, Long keyResultId, Double reportedValue, Integer confidenceLevel, String notes,
            Long submittedByUserId, LocalDateTime submittedAt, ReviewStatus reviewStatus, Long reviewedByUserId,
            LocalDateTime reviewedAt, String rejectionReason) {
        this.id = id;
        this.keyResultId = keyResultId;
        this.reportedValue = reportedValue;
        this.confidenceLevel = confidenceLevel;
        this.notes = notes;
        this.submittedByUserId = submittedByUserId;
        this.submittedAt = submittedAt;
        this.reviewStatus = reviewStatus;
        this.reviewedByUserId = reviewedByUserId;
        this.reviewedAt = reviewedAt;
        this.rejectionReason = rejectionReason;
    }

    public Long getId() {
        return id;
    }

    public Long getKeyResultId() {
        return keyResultId;
    }

    public Double getReportedValue() {
        return reportedValue;
    }

    public Integer getConfidenceLevel() {
        return confidenceLevel;
    }

    public String getNotes() {
        return notes;
    }

    public Long getSubmittedByUserId() {
        return submittedByUserId;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public Long getReviewedByUserId() {
        return reviewedByUserId;
    }

    public LocalDateTime getReviewedAt() {
        return reviewedAt;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKeyResultId(Long keyResultId) {
        this.keyResultId = keyResultId;
    }

    public void setReportedValue(Double reportedValue) {
        this.reportedValue = reportedValue;
    }

    public void setConfidenceLevel(Integer confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setSubmittedByUserId(Long submittedByUserId) {
        this.submittedByUserId = submittedByUserId;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public void setReviewStatus(ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public void setReviewedByUserId(Long reviewedByUserId) {
        this.reviewedByUserId = reviewedByUserId;
    }

    public void setReviewedAt(LocalDateTime reviewedAt) {
        this.reviewedAt = reviewedAt;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    



}