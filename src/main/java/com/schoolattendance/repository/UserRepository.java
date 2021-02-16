package com.schoolattendance.repository;

import org.springframework.stereotype.Repository;

import com.schoolattendance.models.User;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {

	public User findByEmailAndPassword(String email, String password);
}
