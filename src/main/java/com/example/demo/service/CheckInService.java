package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CheckIn;
import com.example.demo.entity.CheckIn.ReviewStatus;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CheckInRepository;

@Service
public class CheckInService {

    private final CheckInRepository repo;

    public CheckInService(CheckInRepository repo) {
        this.repo = repo;
    }

    public CheckIn submitCheckIn(CheckIn checkIn) {

        boolean exists =
                repo.existsByKeyResultIdAndReviewStatusAndSubmittedByUserId(
                        checkIn.getKeyResultId(),
                        ReviewStatus.PENDING,
                        checkIn.getSubmittedByUserId());

        if(exists){
            throw new BusinessValidationException(
                    "You already have a pending check-in");
        }

        checkIn.setReviewStatus(ReviewStatus.PENDING);

        return repo.save(checkIn);
    }

    public List<CheckIn> getAllCheckIns(){
        return repo.findAll();
    }

    public CheckIn getCheckInById(Long id){

        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "CheckIn not found"));
    }

    public CheckIn approveCheckIn(
            Long checkInId,
            Long reviewerId){

        CheckIn c = getCheckInById(checkInId);

        if(c.getReviewStatus() != ReviewStatus.PENDING){
            throw new BusinessValidationException(
                    "Only PENDING check-ins can be approved");
        }

        c.setReviewStatus(ReviewStatus.APPROVED);
        c.setReviewedByUserId(reviewerId);
        c.setReviewedAt(LocalDateTime.now());

        return repo.save(c);
    }

    public CheckIn rejectCheckIn(
            Long checkInId,
            Long reviewerId,
            String reason){

        CheckIn c = getCheckInById(checkInId);

        if(c.getReviewStatus() != ReviewStatus.PENDING){
            throw new BusinessValidationException(
                    "Only PENDING check-ins can be rejected");
        }

        if(reason == null || reason.isBlank()){
            throw new BusinessValidationException(
                    "Rejection reason required");
        }

        c.setReviewStatus(ReviewStatus.REJECTED);
        c.setReviewedByUserId(reviewerId);
        c.setReviewedAt(LocalDateTime.now());
        c.setRejectionReason(reason);

        return repo.save(c);
    }

    public void deleteCheckIn(Long id){
        repo.deleteById(id);
    }
}