package net.javaguides.springboot.dto;

import java.sql.Date;
import java.util.List;

import net.javaguides.springboot.model.Employee;

public class ProjectDto {
	private long id;
	private String projectName;
	private Date startDate;
	private Date endDate;
	private String description;
	private double budget;
	private List<EmployeeDto> employees;
	
	public ProjectDto() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public List<EmployeeDto> getPEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
}
