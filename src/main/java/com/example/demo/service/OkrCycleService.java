package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.OkrCycle;
import com.example.demo.entity.OkrCycle.CycleStatus;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OkrCycleRepository;

@Service
public class OkrCycleService {

    private final OkrCycleRepository repo;

    public OkrCycleService(OkrCycleRepository repo) {
        this.repo = repo;
    }

    public OkrCycle create(OkrCycle cycle, Long userId) {

        if (cycle.getEndDate().isBefore(cycle.getStartDate())) {
            throw new BusinessValidationException(
                    "End date must be after start date");
        }

        cycle.setCreatedBy(userId);
        cycle.setStatus(CycleStatus.DRAFT);

        return repo.save(cycle);
    }

    public List<OkrCycle> getAll() {
        return repo.findAllByOrderByStartDateDesc();
    }

    public OkrCycle getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cycle not found"));
    }

    public OkrCycle activate(Long id) {

        OkrCycle cycle = getById(id);

        if (cycle.getStatus() != CycleStatus.DRAFT) {
            throw new BusinessValidationException(
                    "Only DRAFT cycle can be activated");
        }

        if (repo.existsByStatus(CycleStatus.ACTIVE)) {
            throw new BusinessValidationException(
                    "Another cycle already ACTIVE");
        }

        if (cycle.getStartDate().isAfter(LocalDate.now())) {
            throw new BusinessValidationException(
                    "Start date is in future");
        }

        cycle.setStatus(CycleStatus.ACTIVE);
        return repo.save(cycle);
    }

    public OkrCycle close(Long id) {

        OkrCycle cycle = getById(id);

        if (cycle.getStatus() != CycleStatus.ACTIVE) {
            throw new BusinessValidationException(
                    "Only ACTIVE cycle can be closed");
        }

        cycle.setStatus(CycleStatus.CLOSED);

        // (simple version) - objectives close logic later
        return repo.save(cycle);
    }

    public OkrCycle update(Long id, OkrCycle updated) {

        OkrCycle cycle = getById(id);

        if (cycle.getStatus() != CycleStatus.DRAFT) {
            throw new BusinessValidationException(
                    "Only DRAFT cycle can be edited");
        }

        cycle.setTitle(updated.getTitle());
        cycle.setCycleType(updated.getCycleType());
        cycle.setStartDate(updated.getStartDate());
        cycle.setEndDate(updated.getEndDate());

        return repo.save(cycle);
    }

    public void delete(Long id) {

        OkrCycle cycle = getById(id);

        if (cycle.getStatus() != CycleStatus.DRAFT) {
            throw new BusinessValidationException(
                    "Only DRAFT cycle can be deleted");
        }

        repo.delete(cycle);
    }
}