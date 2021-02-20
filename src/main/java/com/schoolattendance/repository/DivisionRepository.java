package com.schoolattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolattendance.models.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

}
