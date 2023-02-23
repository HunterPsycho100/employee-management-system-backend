package net.javaguides.springboot.dto;

public class EmployeeProjectDto {
	private long id;
	private EmployeeDto employee;
	private ProjectDto project;
	
	public EmployeeProjectDto() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public EmployeeDto getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}
	public ProjectDto getProject() {
		return project;
	}
	public void setProject(ProjectDto project) {
		this.project = project;
	}
}
