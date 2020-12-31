package com.example.springFarmaci.repository;

import com.example.springFarmaci.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    @Query(value = "SELECT * FROM roles WHERE userId = :userId", nativeQuery = true)
    public Roles getRole(@Param("userId") Long userId);
}
