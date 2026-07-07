package com.example.demo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.OkrCycle;
import com.example.demo.service.OkrCycleService;

@RestController
@RequestMapping("/cycles")
public class OkrCycleController {

    private final OkrCycleService service;

    public OkrCycleController(OkrCycleService service) {
        this.service = service;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('PERFORMANCE_ADMIN')")
    public OkrCycle create(@RequestBody OkrCycle cycle,@RequestParam Long userId) {
        return service.create(cycle, userId);
    }

    @GetMapping("/all")
    public List<OkrCycle> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OkrCycle getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PERFORMANCE_ADMIN')")
    public OkrCycle update(@PathVariable Long id,@RequestBody OkrCycle cycle) {
        return service.update(id, cycle);
    }

    @PutMapping("/{id}/activate")
    public OkrCycle activate(@PathVariable Long id) {
        return service.activate(id);
    }

    @PutMapping("/{id}/close")
    public OkrCycle close(@PathVariable Long id) {
        return service.close(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Cycle deleted successfully";
    }
}