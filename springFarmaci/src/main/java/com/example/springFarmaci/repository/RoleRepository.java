package com.example.springFarmaci.repository;

import com.example.springFarmaci.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
}
