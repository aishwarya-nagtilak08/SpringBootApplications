package com.schoolattendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolattendance.models.Attendance;

@Service
public interface AttendanceService {

	public Attendance save(Attendance attendance);

	public Attendance update(Attendance attendance);

	public void delete(Long id);

	public List<Attendance> fetchAll();

	public Attendance findById(Long id);
}
