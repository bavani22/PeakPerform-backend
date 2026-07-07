package com.example.demo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.CheckIn;
import com.example.demo.service.CheckInService;

@RestController
@RequestMapping("/check-ins")
public class CheckInController {

    private final CheckInService service;

    public CheckInController(CheckInService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    @PreAuthorize("hasRole('GOAL_OWNER')")
    public CheckIn submitCheckIn(
            @RequestBody CheckIn checkIn){

        return service.submitCheckIn(checkIn);
    }

    @GetMapping("/all")
    public List<CheckIn> getAll(){
        return service.getAllCheckIns();
    }

    @GetMapping("/{id}")
    public CheckIn getById(
            @PathVariable Long id){

        return service.getCheckInById(id);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('TEAM_LEAD','PERFORMANCE_ADMIN')")
    public CheckIn approve(@PathVariable Long id,@RequestParam Long reviewerId){

        return service.approveCheckIn(id,reviewerId);
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('TEAM_LEAD','PERFORMANCE_ADMIN')")
    public CheckIn reject(@PathVariable Long id,@RequestParam Long reviewerId,@RequestParam String reason){

        return service.rejectCheckIn(id,reviewerId,reason);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PERFORMANCE_ADMIN')")
    public String delete(@PathVariable Long id){
        service.deleteCheckIn(id);
        return "Deleted Successfully";
}
}