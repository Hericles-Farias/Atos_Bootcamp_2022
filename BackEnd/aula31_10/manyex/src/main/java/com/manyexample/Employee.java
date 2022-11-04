package com.manyexample;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity /* criacao da tabela */
public class Employee {

	 @Id /*chave primaria */
     @GeneratedValue( strategy= GenerationType.IDENTITY)  /*auto incrementa por responsabilidade do bd */
     private int eid;
     private String ename;
     private double salary;
     private String deg;
         
     @ManyToMany(targetEntity=Department.class)
     @JoinColumn(name = "eid", referencedColumnName = "id")
     //private Set departmentSet;
     private List departmentSet;     
	
    public Employee(int eid, String ename, double salary, String deg, List departmentSet) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.salary = salary;
		this.deg = deg;
		this.departmentSet = departmentSet;
	}
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDeg() {
		return deg;
	}

	public void setDeg(String deg) {
		this.deg = deg;
	}

	//public Set getDepartmentSet() {
	public List getDepartmentSet() {
		return departmentSet;
	}

	//public void setDepartmentSet(Set departmentSet) {
	public void setDepartmentSet(List departmentSet) {
		this.departmentSet = departmentSet;
	}
	 
         
     
}