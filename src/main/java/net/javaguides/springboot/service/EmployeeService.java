package net.javaguides.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import net.javaguides.springboot.dto.AddressDto;
import net.javaguides.springboot.dto.EmployeeDto;
import net.javaguides.springboot.dto.ProjectDto;
import net.javaguides.springboot.model.Address;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Project;
import net.javaguides.springboot.respository.AddressRepository;
import net.javaguides.springboot.respository.EmployeeRepository;
import net.javaguides.springboot.respository.ProjectRepository;

@Service
public class EmployeeService {
	private EmployeeRepository _employeeRepository;
	private AddressRepository _addressRepository;
	private ProjectRepository _projectRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		_employeeRepository = employeeRepository;
	}
	
//	@Autowired
//	public EmployeeService(AddressRepository addressRepository) {
//		_addressRepository = addressRepository;
//	}
	
	public EmployeeDto getEmployeeById (long id) {
		Employee employee = _employeeRepository.findById(id).orElseThrow();
		EmployeeDto employeeDto = convertToEmployeeDto(employee);
		return employeeDto;
	}
	
	//gets the list of employees
	//Uses the EmployeeRepo to retrieve all employees from the database
	//then iterates over the list of employees, creating a new Dto for each employee
	//then returns a list of EmployeeDto objects in the response
	public List<EmployeeDto> getEmployees() {
		return _employeeRepository
				.findAll()
				.stream()
				.map(EmployeeService::convertToEmployeeDto)
				.collect(Collectors.toList());
	}
	
	//get all addresses
	public List<AddressDto> getAddresses() {
		return _addressRepository
				.findAll()
				.stream()
				.map(EmployeeService::convertToAddressDto)
				.collect(Collectors.toList());
	}
	
	//create employee
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee = convertToEmployee(employeeDto);
		employee = _employeeRepository.save(employee);
		
		EmployeeDto createdEmployee = new EmployeeDto();
		createdEmployee = convertToEmployeeDtoWithoutAddress(employee);
		
		return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	}
	
	public static EmployeeDto convertToEmployeeDtoWithoutAddress (Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setEmailId(employee.getEmailId());
		
		return employeeDto;
	}
	
	public static EmployeeDto convertToEmployeeDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setEmailId(employee.getEmailId());
		// get list of address by employee's id
		List<Address> addresses = employee.getAddresses();
		// convert list of address entities to list of address dtos 
		// by traversing the address list found in employee object
		List<AddressDto> addressDtos = new ArrayList<>();
		for (Address address : addresses) {
			//init addresDto object to store list of addressDtos
			AddressDto addressDto = new AddressDto();
			addressDto = convertToAddressDto(address);
			addressDto.setEmployeeId(employee.getId());
			addressDtos.add(addressDto);
		}			
		employeeDto.setAddresses(addressDtos);
		
		return employeeDto;
	}
	
	public static EmployeeDto convertToEmployeeProjectsDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setEmailId(employee.getEmailId());
		// get list of address by employee's id
		List<Address> addresses = employee.getAddresses();
		// convert list of address entities to list of address dtos 
		// by traversing the address list found in employee object
		List<AddressDto> addressDtos = new ArrayList<>();
		for (Address address : addresses) {
			//init addresDto object to store list of addressDtos
			AddressDto addressDto = new AddressDto();
			addressDto = convertToAddressDto(address);
			addressDto.setEmployeeId(employee.getId());
			addressDtos.add(addressDto);
		}			
		employeeDto.setAddresses(addressDtos);
		
		List<Project> projects = employee.getProjects();
		List<ProjectDto> projectDtos = new ArrayList<>();
		for (Project project : projects) {
			ProjectDto projectDto = new ProjectDto();
			projectDto = convertToProjectDto(project);
			projectDtos.add(projectDto);
		}
		employeeDto.setProjects(projectDtos);
		return employeeDto;
	}
	public static AddressDto convertToAddressDto(Address address) {
		AddressDto addressDto = new AddressDto();
		addressDto.setId(address.getId());
		addressDto.setCity(address.getCity());
		addressDto.setUsState(address.getUsState());
		addressDto.setCountry(address.getCountry());
		addressDto.setPostalCode(address.getPostalCode());
		addressDto.setAddressType(address.getAddressType());
		addressDto.setAddress(address.getAddress());
		//addressDto.setEmployeeId(employee.getId());
		
		return addressDto;
	}
	
	// Project and ProjectDto conversion
	public static ProjectDto convertToProjectDto(Project project) {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(project.getId());
		projectDto.setStartDate(project.getStartDate());
		projectDto.setEndDate(project.getEndDate());
		projectDto.setProjectName(project.getProjectName());
		projectDto.setDescription(project.getDescription());
		projectDto.setBudget(project.getBudget());
		
		return projectDto;
		
	}
	
	public static Project convertToProject(ProjectDto projectDto) {
		Project project = new Project();
		project.setId(projectDto.getId());
		project.setStartDate(projectDto.getStartDate());
		project.setEndDate(projectDto.getEndDate());
		project.setProjectName(projectDto.getProjectName());
		project.setDescription(projectDto.getDescription());
		project.setBudget(projectDto.getBudget());
		
		return project;
		
	}
	
	public static Address convertToAddress(AddressDto addressDto) {
		Address address = new Address();
		address.setId(addressDto.getId());
		address.setCity(addressDto.getCity());
		address.setUsState(addressDto.getUsState());
		address.setCountry(addressDto.getCountry());
		address.setPostalCode(addressDto.getPostalCode());
		address.setAddressType(addressDto.getAddressType());
		address.setAddress(addressDto.getAddress());
		//address.setEmployeeId(employee);
		return address;
	}
	public static Employee convertToEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmailId(employeeDto.getEmailId());
		
		return employee;
	}
}
