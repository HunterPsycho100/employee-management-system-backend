package net.javaguides.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.dto.AddressDto;
import net.javaguides.springboot.dto.EmployeeDto;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Address;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.respository.AddressRepository;
import net.javaguides.springboot.respository.EmployeeRepository;
import net.javaguides.springboot.service.AddressService;
import net.javaguides.springboot.service.EmployeeService;


@CrossOrigin("*")

@RestController
//typical url end point we use in REST apis
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	//inject employee repository
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("employees")
	public List<EmployeeDto> getAllEmployees() {
		return employeeService.getEmployees();
	}
	
	//post to create just employee info (no addresses) using DTO
	@PostMapping("/employees")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		return employeeService.createEmployee(employeeDto);
	}
	
	//Get addresses using Dto
	@GetMapping("/employees/{id}/addresses")
	public List<AddressDto> getAllAddressesById(@PathVariable long id) {	
		return addressService.getAddressesByEmployeeId(id);
	}
	
	// post for address dto	
	@PostMapping("/employees/{id}/addresses") 
	public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {		
		return addressService.createAddress(addressDto);
		
	}
	
	// get employee by id rest api DTO
	@GetMapping("/employees/{id}")
	public EmployeeDto getEmployeeById(@PathVariable long id) {
		return employeeService.getEmployeeById(id);
	}
	
	// update employee rest api	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		// retrieve an employee from the database
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
		
		// update that employee object with the setters
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		// store that employee object into the database
		Employee updatedEmployee = employeeRepository.save(employee);
		
		// returned the employee object to the client
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// delete employee rest api	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		// retrieve an employee from the database
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		// called delete method of employeeRepository and we pass the employee object to the delete method
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		
		// create a map with an entry as deleted True and we simply return that map to the client
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
//	//post to create just employee info (no addresses) using DTO
//		@PostMapping("/employees")
//		public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
//			Employee employee = new Employee();
//			employee.setFirstName(employeeDto.getFirstName());
//			employee.setLastName(employeeDto.getLastName());
//			employee.setEmailId(employeeDto.getEmailId());
//			employee = employeeRepository.save(employee);
//			
//			EmployeeDto createdEmployee = new EmployeeDto();
//			createdEmployee.setFirstName(employeeDto.getFirstName());
//			createdEmployee.setLastName(employeeDto.getLastName());
//			createdEmployee.setEmailId(employeeDto.getEmailId());
//			return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
//		}
	
	//Commented referenced code
//	@GetMapping("employees")
//	public List<EmployeeDto> getAllEmployees() {
//		List<Employee> employees = employeeRepository.findAll();
//		List<EmployeeDto> employeeDtos = new ArrayList<>();
//		for (Employee employee : employees) {
//			EmployeeDto employeeDto = new EmployeeDto();
//			employeeDto.setId(employee.getId());
//			employeeDto.setFirstName(employee.getFirstName());
//			employeeDto.setLastName(employee.getLastName());
//			employeeDto.setEmailId(employee.getEmailId());
//			// get list of address by employee's id
//			List<Address> addresses = employee.getAddresses();
//			// convert list of address entities to list of address dtos 
//			// by traversing the address list found in employee object
//			List<AddressDto> addressDtos = new ArrayList<>();
//			for (Address address : addresses) {
//				//init addresDto object to store list of addressDtos
//				AddressDto addressDto = new AddressDto();
//				addressDto.setId(address.getId());
//				addressDto.setCity(address.getCity());
//				addressDto.setUsState(address.getUsState());
//				addressDto.setCountry(address.getCountry());
//				addressDto.setPostalCode(address.getPostalCode());
//				addressDto.setAddressType(address.getAddressType());
//				addressDto.setAddress(address.getAddress());
//				addressDto.setEmployeeId(employee.getId());
//				addressDtos.add(addressDto);
//			}			
//			employeeDto.setAddresses(addressDtos);
//			
//			//employeeDto.setAddresses(employee.getAddresses());
//			
//			employeeDtos.add(employeeDto);
//		}
//		return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
//	}
	
	// create employee rest API
//	@PostMapping("/employees")
//	public Employee createEmployee(@RequestBody Employee employee) {
//		return employeeRepository.save(employee);
//	}
	
	// create address rest API
//	@PostMapping("/employees/{id}/addresses")
//	public Address createAddress(@RequestBody Address address) {
//		return addressRepository.save(address);
//	}
	
	//Get addresses using Dto
//	@GetMapping("/employees/{id}/addresses")
//	public List<AddressDto> getAllAddressesById(@PathVariable long id) {	

//		// use id to find employee object
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));		
//		
//		// get list of address by employee's id
//		List<Address> addresses = employee.getAddresses();
//		
//		// convert list of address entities to list of address dtos 
//		// by traversing the address list found in employee object
//		List<AddressDto> addressDtos = new ArrayList<>();
//		for (Address address : addresses) {
//			//init addresDto object to store list of addressDtos
//			AddressDto addressDto = new AddressDto();
//			addressDto.setId(address.getId());
//			addressDto.setCity(address.getCity());
//			addressDto.setUsState(address.getUsState());
//			addressDto.setCountry(address.getCountry());
//			addressDto.setPostalCode(address.getPostalCode());
//			addressDto.setAddressType(address.getAddressType());
//			addressDto.setAddress(address.getAddress());
//			addressDto.setEmployeeId(id);
//			addressDtos.add(addressDto);
//		}
//		return new ResponseEntity<>(addressDtos, HttpStatus.OK);
//	}
	
//	@PostMapping("/employees/{id}/addresses") 
//	public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
//		Employee employee = employeeRepository.findById(addressDto.getEmployeeId())
//				.orElseThrow(()-> new EntityNotFoundException("Parent not found"));
//		Address address = new Address();
//		//address.setId(address.getId());
//		address.setCity(addressDto.getCity());
//		address.setUsState(addressDto.getUsState());
//		address.setCountry(addressDto.getCountry());
//		address.setPostalCode(addressDto.getPostalCode());
//		address.setAddressType(addressDto.getAddressType());
//		address.setAddress(addressDto.getAddress());
//		address.setEmployeeId(employee);
//		address = addressRepository.save(address);
//		
//		AddressDto createdAddress = new AddressDto();
//		createdAddress.setId(address.getId());
//		createdAddress.setCity(addressDto.getCity());
//		createdAddress.setUsState(addressDto.getUsState());
//		createdAddress.setCountry(addressDto.getCountry());
//		createdAddress.setPostalCode(addressDto.getPostalCode());
//		createdAddress.setAddressType(addressDto.getAddressType());
//		createdAddress.setAddress(addressDto.getAddress());
//		createdAddress.setEmployeeId(employee.getId());
//		
//		return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
//		
//	}
	
	// get employee by id rest api DTO
//	@GetMapping("/employees/{id}")
//	public EmployeeDto getEmployeeById(@PathVariable long id) {
//	
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
//		
//		EmployeeDto employeeDto = new EmployeeDto();
//		employeeDto.setId(id);
//		employeeDto.setFirstName(employee.getFirstName());
//		employeeDto.setLastName(employee.getLastName());
//		employeeDto.setEmailId(employee.getEmailId());
//		
//		
//		// get list of address by employee's id
//		List<Address> addresses = employee.getAddresses();
//		// convert list of address entities to list of address dtos 
//		// by traversing the address list found in employee object
//		List<AddressDto> addressDtos = new ArrayList<>();
//		for (Address address : addresses) {
//			//init addresDto object to store list of addressDtos
//			AddressDto addressDto = new AddressDto();
//			addressDto.setId(address.getId());
//			addressDto.setCity(address.getCity());
//			addressDto.setUsState(address.getUsState());
//			addressDto.setCountry(address.getCountry());
//			addressDto.setPostalCode(address.getPostalCode());
//			addressDto.setAddressType(address.getAddressType());
//			addressDto.setAddress(address.getAddress());
//			addressDto.setEmployeeId(id);
//			addressDtos.add(addressDto);
//		}
//		//employeeDto.setAddresses(employee.getAddresses());
//		employeeDto.setAddresses(addressDtos);
//		
//		return new ResponseEntity<>(employeeDto, HttpStatus.OK);
//	}	
	
	
	// get employee by id rest api
//	@GetMapping("/employees/{id}")
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
//		
//		return ResponseEntity.ok(employee);
//	}
}
