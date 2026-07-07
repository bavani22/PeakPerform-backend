package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ProgressSnapshot;
import com.example.demo.service.ProgressSnapshotService;

@RestController
@RequestMapping("/snapshots")
public class ProgressSnapshotController {

    private final ProgressSnapshotService service;

    public ProgressSnapshotController(ProgressSnapshotService service) {
        this.service = service;
    }

    @PostMapping("/{objectiveId}")
    @PreAuthorize("hasAnyRole('GOAL_OWNER','PERFORMANCE_ADMIN','TEAM_LEAD')")
    public ProgressSnapshot capture(@PathVariable Long objectiveId,@RequestParam Long userId,@RequestParam Double progressPercent) {

        return service.captureSnapshot(objectiveId, userId, progressPercent);
    }

    @GetMapping("/{objectiveId}/trend")
    @PreAuthorize("isAuthenticated()")
    public List<ProgressSnapshot> trend(@PathVariable Long objectiveId,@RequestParam(required = false) LocalDate from,@RequestParam(required = false) LocalDate to) {

        return service.getTrend(objectiveId, from, to);
    }

    @GetMapping("/{objectiveId}")
    @PreAuthorize("isAuthenticated()")
    public List<ProgressSnapshot> getAll(@PathVariable Long objectiveId) {
        return service.getAll(objectiveId);
    }
}