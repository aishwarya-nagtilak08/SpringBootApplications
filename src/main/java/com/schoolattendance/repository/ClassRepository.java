package com.schoolattendance.repository;

import org.springframework.stereotype.Repository;

import com.schoolattendance.models.ClassObject;

@Repository
public interface ClassRepository extends GenericRepository<ClassObject, Long> {

}
