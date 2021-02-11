package com.schoolattendance.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "attendance")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date date;
	private String status;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinTable(name = "class_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private ClassObject classObj;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinTable(name = "student_id")
	private User stuendt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ClassObject getClassObj() {
		return classObj;
	}

	public void setClassObj(ClassObject classObj) {
		this.classObj = classObj;
	}

	public User getStuendt() {
		return stuendt;
	}

	public void setStuendt(User stuendt) {
		this.stuendt = stuendt;
	}

}
