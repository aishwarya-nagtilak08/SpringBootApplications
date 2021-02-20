package com.schoolattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolattendance.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
