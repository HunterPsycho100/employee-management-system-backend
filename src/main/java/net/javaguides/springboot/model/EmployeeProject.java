package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "employeesprojects")
public class EmployeeProject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @JoinColumn (name = "id")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "employee_id")
	private long employeeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "project_id")
	private long projectId;
	
	public EmployeeProject() {}
	
	public EmployeeProject(long employeeId, long projectId) {
		super();
		this.employeeId = employeeId;
		this.projectId = projectId;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
}
