package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProgressSnapshot;

public interface ProgressSnapshotRepository extends JpaRepository<ProgressSnapshot, Long> {

    List<ProgressSnapshot> findByObjectiveIdOrderBySnapshotDateAsc(Long objectiveId);

    boolean existsByObjectiveIdAndSnapshotDate(Long objectiveId, LocalDate snapshotDate);

    List<ProgressSnapshot> findByObjectiveIdAndSnapshotDateBetweenOrderBySnapshotDateAsc(
            Long objectiveId,
            LocalDate from,
            LocalDate to
    );
}