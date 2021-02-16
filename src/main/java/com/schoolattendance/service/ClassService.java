package com.schoolattendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolattendance.models.Attendance;
import com.schoolattendance.models.ClassObject;

@Service
public interface ClassService {

	public ClassObject save(ClassObject classObject);

	public ClassObject update(ClassObject classObject);

	public void delete(Long id);

	public List<ClassObject> fetchAll();

	public ClassObject findById(Long id);
}
