package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.KeyResult;
import com.example.demo.service.KeyResultService;

@RestController
@RequestMapping("/api/key-results")
public class KeyResultController {

    private final KeyResultService service;

    public KeyResultController(KeyResultService service) {
        this.service = service;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('GOAL_OWNER','PERFORMANCE_ADMIN')")
    public KeyResult create(@RequestBody KeyResult kr) {
        return service.create(kr);
    }

    @GetMapping("/objective/{objectiveId}")
    @PreAuthorize("isAuthenticated()")
    public List<KeyResult> getAll(@RequestParam Long objectiveId) {
        return service.getAll(objectiveId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public KeyResult getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('GOAL_OWNER','PERFORMANCE_ADMIN')")
    public KeyResult update(@PathVariable Long id,@RequestBody KeyResult kr) {
        return service.update(id, kr);
    }

    @PatchMapping("/{id}/value")
    @PreAuthorize("hasAnyRole('GOAL_OWNER','PERFORMANCE_ADMIN')")
    public KeyResult updateValue(@PathVariable Long id,@RequestBody Map<String, Object> body) {

        Double value = Double.valueOf(body.get("currentValue").toString());
        String status = body.get("status") != null ? body.get("status").toString() : null;

        return service.updateValue(id, value, status);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('GOAL_OWNER','PERFORMANCE_ADMIN')")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "KeyResult deleted successfully.";
    }
}