package com.schoolattendance.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericRepository<T,ID extends Serializable> extends JpaRepository<T, ID> {

}
