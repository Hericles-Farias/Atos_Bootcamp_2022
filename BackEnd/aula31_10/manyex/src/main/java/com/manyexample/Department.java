package com.manyexample;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Department {

	@Id 
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	private int id;
	private String name;
	
	@ManyToMany(targetEntity=Employee.class)
	//private Set employeeSet;
	private List employeeSet;
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public Department(String name, List employeeSet) {
		super();
		this.id = id;
		this.name = name;
		this.employeeSet = employeeSet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//public Set getEmployeeSet() {
	public List getEmployeeSet() {
		return employeeSet;
	}

	//public void setEmployeeSet(Set employeeSet) {
	public void setEmployeeSet(List employeeSet) {
		this.employeeSet = employeeSet;
	}

	
	
	
}