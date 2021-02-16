package com.schoolattendance.repository;

import org.springframework.stereotype.Repository;

import com.schoolattendance.models.Role;

@Repository
public interface RoleRepository extends GenericRepository<Role, Long> {

}
