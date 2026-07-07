package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CheckIn;
import com.example.demo.entity.CheckIn.ReviewStatus;

public interface CheckInRepository
        extends JpaRepository<CheckIn, Long> {

    List<CheckIn> findByKeyResultId(Long keyResultId);

    List<CheckIn> findBySubmittedByUserId(Long userId);

    List<CheckIn> findByReviewStatus(ReviewStatus status);

    boolean existsByKeyResultIdAndReviewStatusAndSubmittedByUserId(
            Long keyResultId,
            ReviewStatus reviewStatus,
            Long userId);

    long countByReviewStatus(ReviewStatus status);
}