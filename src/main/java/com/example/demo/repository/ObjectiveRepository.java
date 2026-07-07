package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Objective;
import com.example.demo.entity.Objective.ObjectiveStatus;

public interface ObjectiveRepository extends JpaRepository<Objective, Long> {

    List<Objective> findByOwnerId(Long ownerId);

    List<Objective> findByCycleId(Long cycleId);

    List<Objective> findByCycleIdAndStatus(Long cycleId, ObjectiveStatus status);
}