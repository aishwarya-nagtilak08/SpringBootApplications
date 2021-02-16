package com.schoolattendance.repository;

import org.springframework.stereotype.Repository;

import com.schoolattendance.models.Attendance;

@Repository
public interface AttendanceRepository extends GenericRepository<Attendance, Long> {

}
