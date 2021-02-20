package com.schoolattendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolattendance.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailAndPassword(String email, String password);
}
