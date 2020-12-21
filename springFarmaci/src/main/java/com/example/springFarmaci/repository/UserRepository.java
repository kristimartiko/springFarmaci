package com.example.springFarmaci.repository;

import com.example.springFarmaci.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user WHERE email = :email AND to_date IS NULL ", nativeQuery = true)
    public User findByEmail(@Param("email") String value);

    @Query(value = "SELECT * FROM user WHERE to_date IS NULL", nativeQuery = true)
    public List<User> findAll();

}
