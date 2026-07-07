package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.KeyResult;
import com.example.demo.entity.KeyResult.KeyResultStatus;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.KeyResultRepository;

@Service
public class KeyResultService {

    private final KeyResultRepository repo;

    public KeyResultService(KeyResultRepository repo) {
        this.repo = repo;
    }

    public KeyResult create(KeyResult kr) {

        if (kr.getMetricType() == KeyResult.MetricType.BOOLEAN) {
            kr.setTargetValue(1.0);
            kr.setCurrentValue(0.0);
        }

        kr.setStatus(KeyResultStatus.ON_TRACK);
        return repo.save(kr);
    }

    public List<KeyResult> getAll(Long objectiveId) {
        return repo.findByObjectiveId(objectiveId);
    }

    public KeyResult getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("KeyResult not found"));
    }

    public KeyResult update(Long id, KeyResult updated) {

        KeyResult kr = getById(id);

        if (kr.getStatus() == KeyResultStatus.COMPLETED) {
            throw new BusinessValidationException("Cannot edit COMPLETED Key Result");
        }

        if (updated.getTargetValue() < kr.getCurrentValue()) {
            throw new BusinessValidationException(
                    "Target cannot be less than current value");
        }

        kr.setTitle(updated.getTitle());
        kr.setTargetValue(updated.getTargetValue());
        kr.setUnit(updated.getUnit());
        kr.setDueDate(updated.getDueDate());

        return repo.save(kr);
    }

    public KeyResult updateValue(Long id, Double newValue, String newStatus) {

        KeyResult kr = getById(id);

        if (kr.getStatus() == KeyResultStatus.COMPLETED) {
            throw new BusinessValidationException("Cannot update COMPLETED Key Result");
        }

        if (newValue < 0) {
            throw new BusinessValidationException("Value cannot be negative");
        }

        if (kr.getMetricType() == KeyResult.MetricType.BOOLEAN && newValue > 1.0) {
            throw new BusinessValidationException("BOOLEAN allows only 0 or 1");
        }

        kr.setCurrentValue(newValue);

        if (newStatus != null) {
            kr.setStatus(KeyResultStatus.valueOf(newStatus));
        } else {
            double percent = (newValue / kr.getTargetValue()) * 100;

            if (percent >= 100) kr.setStatus(KeyResultStatus.COMPLETED);
            else if (percent >= 60) kr.setStatus(KeyResultStatus.ON_TRACK);
            else if (percent >= 30) kr.setStatus(KeyResultStatus.AT_RISK);
            else kr.setStatus(KeyResultStatus.BEHIND);
        }

        return repo.save(kr);
    }

    public void delete(Long id) {

        KeyResult kr = getById(id);

        if (kr.getStatus() == KeyResultStatus.COMPLETED) {
            throw new BusinessValidationException("Cannot delete COMPLETED Key Result");
        }

        repo.delete(kr);
    }
}