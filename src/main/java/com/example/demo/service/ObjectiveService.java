package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Objective;
import com.example.demo.entity.Objective.ObjectiveStatus;
import com.example.demo.exception.BusinessValidationException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ObjectiveRepository;

@Service
public class ObjectiveService {

    private final ObjectiveRepository repo;

    public ObjectiveService(ObjectiveRepository repo) {
        this.repo = repo;
    }

    public Objective create(Objective obj) {
        obj.setStatus(ObjectiveStatus.DRAFT);
        obj.setProgressPercent(0.0);
        return repo.save(obj);
    }

    public List<Objective> getAll() {
        return repo.findAll();
    }

    public Objective getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Objective not found"));
    }

    public Objective update(Long id, Objective obj) {

        Objective existing = getById(id);

        if (existing.getStatus() == ObjectiveStatus.COMPLETED ||
            existing.getStatus() == ObjectiveStatus.CANCELLED) {

            throw new BusinessValidationException(
                    "Cannot edit this objective");
        }

        existing.setTitle(obj.getTitle());
        existing.setDescription(obj.getDescription());
        existing.setTeamLeadId(obj.getTeamLeadId());

        return repo.save(existing);
    }

    public Objective activate(Long id) {

        Objective obj = getById(id);

        if (obj.getStatus() != ObjectiveStatus.DRAFT) {
            throw new BusinessValidationException(
                    "Only DRAFT objective can be activated");
        }

        obj.setStatus(ObjectiveStatus.ACTIVE);
        return repo.save(obj);
    }

    public Objective pause(Long id) {

        Objective obj = getById(id);

        if (obj.getStatus() != ObjectiveStatus.ACTIVE) {
            throw new BusinessValidationException(
                    "Only ACTIVE objective can be paused");
        }

        obj.setStatus(ObjectiveStatus.PAUSED);
        return repo.save(obj);
    }

    public Objective resume(Long id) {

        Objective obj = getById(id);

        if (obj.getStatus() != ObjectiveStatus.PAUSED) {
            throw new BusinessValidationException(
                    "Only PAUSED objective can be resumed");
        }

        obj.setStatus(ObjectiveStatus.ACTIVE);
        return repo.save(obj);
    }

    public void delete(Long id) {

        Objective obj = getById(id);

        if (obj.getStatus() != ObjectiveStatus.DRAFT) {
            throw new BusinessValidationException(
                    "Only DRAFT objective can be deleted");
        }

        repo.delete(obj);
    }
}