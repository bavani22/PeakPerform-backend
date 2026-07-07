package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.KeyResult;
import com.example.demo.entity.KeyResult.KeyResultStatus;

public interface KeyResultRepository extends JpaRepository<KeyResult, Long> {

    List<KeyResult> findByObjectiveId(Long objectiveId);

    List<KeyResult> findByObjectiveIdAndStatus(Long objectiveId, KeyResultStatus status);
}