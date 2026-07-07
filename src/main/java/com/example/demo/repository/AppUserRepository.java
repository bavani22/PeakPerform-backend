package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.AppUser.Role;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{

    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);

    List<AppUser> findByRole(Role role);

    List<AppUser> findByDepartmentAndIsActiveTrue(String department);

    List<AppUser> findByIsActiveTrue();

    long countByRoleAndIsActiveTrue(Role role);

}