package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OkrCycle;
import com.example.demo.entity.OkrCycle.CycleStatus;

public interface OkrCycleRepository extends JpaRepository<OkrCycle, Long> {

    List<OkrCycle> findByStatus(CycleStatus status);

    Optional<OkrCycle> findFirstByStatus(CycleStatus status);

    boolean existsByStatus(CycleStatus status);

    List<OkrCycle> findAllByOrderByStartDateDesc();
}