package com.schoolattendance.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "division")
public class Division {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany
	private List<ClassObject> classObjs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ClassObject> getClassObjs() {
		return classObjs;
	}

	public void setClassObjs(List<ClassObject> classObjs) {
		this.classObjs = classObjs;
	}

}
