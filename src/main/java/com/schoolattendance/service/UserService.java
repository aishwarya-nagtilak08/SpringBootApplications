package com.schoolattendance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolattendance.models.Attendance;
import com.schoolattendance.models.User;

@Service
public interface UserService {

	public User save(User user);

	public User update(User user);

	public void delete(Long id);

	public List<User> fetchAll();
	
	public User findById(Long id);
	
	public User login(String email, String password);
}
