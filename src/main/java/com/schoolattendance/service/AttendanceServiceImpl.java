package com.schoolattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolattendance.models.Attendance;
import com.schoolattendance.repository.AttendanceRepository;
@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Override
	public Attendance save(Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

	@Override
	public Attendance update(Attendance attendance) {
		// TODO Auto-generated method stub
		return attendanceRepository.save(attendance);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		attendanceRepository.deleteById(id);
	}

	@Override
	public List<Attendance> fetchAll() {
		// TODO Auto-generated method stub
		return attendanceRepository.findAll();
	}

	@Override
	public Attendance findById(Long id) {
		// TODO Auto-generated method stub
		return attendanceRepository.findById(id).orElse(null);
	}
}
