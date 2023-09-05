package com.tanasvi.multidb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "StudentEntity")
@Table(name = "Student")
@Data
public class StudentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	@Column
	private String name;
	@Column
	private float marks;
}
