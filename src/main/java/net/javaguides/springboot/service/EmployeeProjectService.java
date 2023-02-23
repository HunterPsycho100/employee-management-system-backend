package net.javaguides.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.dto.EmployeeDto;
import net.javaguides.springboot.dto.EmployeeProjectDto;
import net.javaguides.springboot.dto.ProjectDto;
import net.javaguides.springboot.model.EmployeeProject;
import net.javaguides.springboot.model.Project;
import net.javaguides.springboot.respository.EmployeeProjectRepository;

@Service
public class EmployeeProjectService {

	
	private EmployeeProjectRepository _employeeProjectRepository;
	
	@Autowired
	public EmployeeProjectService(EmployeeProjectRepository employeeProjectRepository) {
		_employeeProjectRepository = employeeProjectRepository;
	}
	
	public EmployeeProjectDto getEmployeeProjectById (long id) {
		EmployeeProject employeeProject = _employeeProjectRepository.findById(id).orElseThrow();
		EmployeeProjectDto employeeProjectDto = convertToEmployeeProjectDto(employeeProject);
		return employeeProjectDto;
	}
	
	public static EmployeeProjectDto convertToEmployeeProjectDto (EmployeeProject employeeProject) {
		EmployeeProjectDto employeeProjectDto = new EmployeeProjectDto();
		employeeProjectDto.setId(employeeProject.getId());
		employeeProjectDto.setEmployeeId(employeeProject.getEmployeeId());
		employeeProjectDto.setProjectId(employeeProject.getProjectId());
	
		return employeeProjectDto;
	}
	
	public static EmployeeProject convertToEmployeeProject (EmployeeProjectDto employeeProjectDto) {
		EmployeeProject employeeProject = new EmployeeProject();
		employeeProject.setId(employeeProjectDto.getId());
		employeeProject.setEmployeeId(employeeProjectDto.getEmployeeId());
		employeeProject.setProjectId(employeeProjectDto.getProjectId());
	
		
		return employeeProject;
	}
}
