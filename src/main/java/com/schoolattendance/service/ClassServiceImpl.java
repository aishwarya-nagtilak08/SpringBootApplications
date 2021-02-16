package com.schoolattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.schoolattendance.models.ClassObject;
import com.schoolattendance.repository.ClassRepository;

public class ClassServiceImpl implements ClassService {

	@Autowired
	ClassRepository classRepository;

	@Override
	public ClassObject save(ClassObject classObject) {
		// TODO Auto-generated method stub
		return classRepository.save(classObject);
	}

	@Override
	public ClassObject update(ClassObject classObject) {
		return classRepository.save(classObject);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		classRepository.deleteById(id);
	}

	@Override
	public List<ClassObject> fetchAll() {
		// TODO Auto-generated method stub
		return classRepository.findAll();
	}

	@Override
	public ClassObject findById(Long id) {
		// TODO Auto-generated method stub
		return classRepository.findById(id).orElse(null);
	}

}
