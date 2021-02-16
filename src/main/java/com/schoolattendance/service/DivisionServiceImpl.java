package com.schoolattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.schoolattendance.models.Division;
import com.schoolattendance.repository.DivisionRepository;

public class DivisionServiceImpl implements DivisionService {

	@Autowired
	DivisionRepository divisionRepository;

	@Override
	public Division save(Division division) {
		// TODO Auto-generated method stub
		return divisionRepository.save(division);
	}

	@Override
	public Division update(Division division) {
		// TODO Auto-generated method stub
		return divisionRepository.save(division);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		divisionRepository.deleteById(id);
	}

	@Override
	public List<Division> fetchAll() {
		// TODO Auto-generated method stub
		return divisionRepository.findAll();
	}

	@Override
	public Division findById(Long id) {
		// TODO Auto-generated method stub
		return divisionRepository.findById(id).orElse(null);
	}

}
