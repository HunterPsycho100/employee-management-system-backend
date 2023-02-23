package net.javaguides.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dto.EmployeeDto;
import net.javaguides.springboot.dto.ProjectDto;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Project;
import net.javaguides.springboot.respository.EmployeeRepository;
import net.javaguides.springboot.respository.ProjectRepository;

@Service
public class ProjectService {
	
	private ProjectRepository _projectRepository;
	
	@Autowired
	public ProjectService(ProjectRepository projectRepository) {
		_projectRepository = projectRepository;
	}
	
	public ProjectDto getProjectById (long id) {
		Project project = _projectRepository.findById(id).orElseThrow();
		ProjectDto projectDto = convertToProjectDto(project);
		return projectDto;
	}
	
	//gets the list of employees
	//Uses the EmployeeRepo to retrieve all employees from the database
	//then iterates over the list of employees, creating a new Dto for each employee
	//then returns a list of EmployeeDto objects in the response
	public List<ProjectDto> getProjects() {
		return _projectRepository
				.findAll()
				.stream()
				.map(ProjectService::convertToProjectDto)
				.collect(Collectors.toList());
	}
	
	
	public static ProjectDto convertToProjectDto (Project project) {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(project.getId());
		projectDto.setProjectName(project.getProjectName());
		projectDto.setStartDate(project.getStartDate());
		projectDto.setEndDate(project.getEndDate());
		projectDto.setDescription(project.getDescription());
		projectDto.setBudget(project.getBudget());
		
		return projectDto;
	}
	
	public static Project convertToProject (ProjectDto projectDto) {
		Project project = new Project();
		project.setId(projectDto.getId());
		project.setProjectName(projectDto.getProjectName());
		project.setStartDate(projectDto.getStartDate());
		project.setEndDate(projectDto.getEndDate());
		project.setDescription(projectDto.getDescription());
		project.setBudget(projectDto.getBudget());
		
		return project;
	}
}
