package com.schoolattendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolattendance.models.User;
import com.schoolattendance.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> fetchAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User login(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndPassword(email, password);
	}

}
