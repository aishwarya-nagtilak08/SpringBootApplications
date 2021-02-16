package com.schoolattendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolattendance.models.Attendance;
import com.schoolattendance.models.Division;

@Service
public interface DivisionService {

	public Division save(Division division);

	public Division update(Division division);

	public void delete(Long id);

	public List<Division> fetchAll();
	
	public Division findById(Long id);
}
