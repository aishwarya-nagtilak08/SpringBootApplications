package com.schoolattendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolattendance.models.Attendance;
import com.schoolattendance.models.Role;

@Service
public interface RoleService {

	public Role save(Role role);

	public Role update(Role role);

	public void delete(Long id);

	public List<Role> fetchAll();
	
	public Role findById(Long id);
}
