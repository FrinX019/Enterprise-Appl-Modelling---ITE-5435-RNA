package com.va.week4;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Employee {

	private int empId;
	private String empName;
	private String jobTitle;
	private double salary;
	
	public int getEmpId() {
		return empId;
	}


	public String getEmpName() {
		return empName;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public double getSalary() {
		return salary;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public Employee(int empId, String empName, String jobTitle, double salary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.jobTitle = jobTitle;
		this.salary = salary;
	}
	
	
}
