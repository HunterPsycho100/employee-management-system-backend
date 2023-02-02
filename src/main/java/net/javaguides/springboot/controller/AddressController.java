package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import net.javaguides.springboot.dto.EmployeeDto;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Address;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.respository.AddressRepository;
import net.javaguides.springboot.respository.EmployeeRepository;

@CrossOrigin("*")

@RestController
//typical url end point we use in REST apis
@RequestMapping("/api/v1/employees/")
public class AddressController {
	
	//inject address repository
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
//	// create address rest API
//	@PostMapping("{id}/address")
//	public Address createAddress1(@RequestBody Address address) {
//		return addressRepository.save(address);
//	}
//	
//	// get address by employee's id rest api
//	@GetMapping("{id}/address")
//	public ResponseEntity<List<Address>> getAddressesForEmployee(@PathVariable Long id) {
//		Employee employee = employeeRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
//		
//		List<Address> addresses = employee.getAddress();
//		
//		return ResponseEntity.ok(addresses);
//	}
	
	
	
}
