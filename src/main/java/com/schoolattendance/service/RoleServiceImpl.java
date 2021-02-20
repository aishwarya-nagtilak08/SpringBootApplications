package com.schoolattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolattendance.models.Role;
import com.schoolattendance.repository.RoleRepository;
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public List<Role> fetchAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id).orElse(null);
	}

}
