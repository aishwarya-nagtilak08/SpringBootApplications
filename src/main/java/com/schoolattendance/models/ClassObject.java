package com.schoolattendance.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class ClassObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<Role> teacher;

	private Integer strength;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<User> student;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Division> division;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<User> getTeacher() {
//		return teacher;
//	}
//
//	public void setTeacher(List<User> teacher) {
//		this.teacher = teacher;
//	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public List<User> getStudent() {
		return student;
	}

	public void setStudent(List<User> student) {
		this.student = student;
	}

	public List<Division> getDivision() {
		return division;
	}

	public void setDivision(List<Division> division) {
		this.division = division;
	}

}
