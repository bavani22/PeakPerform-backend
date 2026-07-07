package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ProgressSnapshot;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.repository.ProgressSnapshotRepository;

@Service
public class ProgressSnapshotService {

    private final ProgressSnapshotRepository repo;

    public ProgressSnapshotService(ProgressSnapshotRepository repo) {
        this.repo = repo;
    }

    public ProgressSnapshot captureSnapshot(Long objectiveId,
                                           Long userId,
                                           Double progressPercent) {

        LocalDate today = LocalDate.now();

        if (repo.existsByObjectiveIdAndSnapshotDate(objectiveId, today)) {
            throw new BusinessValidationException(
                    "Snapshot already exists for today");
        }

        ProgressSnapshot ps = new ProgressSnapshot(
                objectiveId,
                today,
                progressPercent,
                userId
        );

        return repo.save(ps);
    }

    public List<ProgressSnapshot> getTrend(Long objectiveId,
                                           LocalDate from,
                                           LocalDate to) {

        if (from != null && to != null && to.isBefore(from)) {
            throw new BusinessValidationException(
                    "'to' date must not be before 'from'");
        }

        if (from != null && to != null) {
            return repo.findByObjectiveIdAndSnapshotDateBetweenOrderBySnapshotDateAsc(
                    objectiveId, from, to);
        }

        return repo.findByObjectiveIdOrderBySnapshotDateAsc(objectiveId);
    }

    public List<ProgressSnapshot> getAll(Long objectiveId) {
        return repo.findByObjectiveIdOrderBySnapshotDateAsc(objectiveId);
    }
}