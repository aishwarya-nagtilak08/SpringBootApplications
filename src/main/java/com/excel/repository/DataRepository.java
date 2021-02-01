package com.excel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.excel.model.DataTO;

@Repository
public interface DataRepository extends CrudRepository<DataTO, Long> {

}
