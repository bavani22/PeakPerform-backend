package com.example.demo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Objective;
import com.example.demo.service.ObjectiveService;

@RestController
@RequestMapping("/objectives")
public class ObjectiveController {

    private final ObjectiveService service;

    public ObjectiveController(ObjectiveService service) {
        this.service = service;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('GOAL_OWNER','PERFORMANCE_ADMIN')")
    public Objective create(@RequestBody Objective obj) {
        return service.create(obj);
    }

    @GetMapping("/all")
    public List<Objective> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Objective getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('GOAL_OWNER','PERFORMANCE_ADMIN')")
    public Objective update(@PathVariable Long id,@RequestBody Objective obj) {
        return service.update(id, obj);
    }

    @PutMapping("/{id}/activate")
    public Objective activate(@PathVariable Long id) {
        return service.activate(id);
    }

    @PutMapping("/{id}/pause")
    public Objective pause(@PathVariable Long id) {
        return service.pause(id);
    }

    @PutMapping("/{id}/resume")
    public Objective resume(@PathVariable Long id) {
        return service.resume(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Objective deleted successfully";
    }
}