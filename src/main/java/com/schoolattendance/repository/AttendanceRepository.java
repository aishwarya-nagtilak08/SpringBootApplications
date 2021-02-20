package com.schoolattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolattendance.models.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}
