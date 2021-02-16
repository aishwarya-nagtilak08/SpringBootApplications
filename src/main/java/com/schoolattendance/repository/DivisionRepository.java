package com.schoolattendance.repository;

import org.springframework.stereotype.Repository;

import com.schoolattendance.models.Division;

@Repository
public interface DivisionRepository extends GenericRepository<Division, Long> {

}
