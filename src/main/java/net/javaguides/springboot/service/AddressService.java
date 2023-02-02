package net.javaguides.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import net.javaguides.springboot.dto.AddressDto;
import net.javaguides.springboot.dto.EmployeeDto;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Address;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.respository.AddressRepository;
import net.javaguides.springboot.respository.EmployeeRepository;

@Service
public class AddressService {
	private AddressRepository _addressRepository;
	private EmployeeRepository _employeeRepository;
	
	@Autowired
	public AddressService(AddressRepository addressRepository) {
		_addressRepository = addressRepository;
	}
	
	@Autowired
	public void EmployeeService(EmployeeRepository employeeRepository) {
		_employeeRepository = employeeRepository;
	}
	
	public List<AddressDto> getAddresses() {
		return _addressRepository
				.findAll()
				.stream()
				.map(EmployeeService::convertToAddressDto)
				.collect(Collectors.toList());
	}
	
	//get employee by id
	public List<AddressDto> getAddressesByEmployeeId(@PathVariable long id) {
		// use id to find employee object
		Employee employee = _employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));		
		
		// get list of address by employee's id
		List<Address> addresses = employee.getAddresses();
		
		// convert list of address entities to list of address dtos 
		// by traversing the address list found in employee object
		List<AddressDto> addressDtos = new ArrayList<>();
		for (Address address : addresses) {
			//init addresDto object to store list of addressDtos
			AddressDto addressDto = new AddressDto();
			addressDto = convertToAddressDto(address);
			addressDto.setEmployeeId(id);
			addressDtos.add(addressDto);
		}
		return addressDtos;
	}
	
	// Method maps the addressDto to the address entity using the request body
		// and associates it with the parent(employee) using the employeeId property
		// from the childDto(addressDto).
		// The method first finds the parent(employee) in the database using the employeeId in the
		// addressDto, if employee not found, throw exception.
		// Then it creates a new child entity, sets the properties and parent, then saves
		// it in the database and return the created child in the response. 
	public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {

		Address address = new Address();
		address = convertToAddress(addressDto);
		address = _addressRepository.save(address);
		
		AddressDto createdAddress = new AddressDto();
		createdAddress = convertToAddressDto(address);
		
		return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
	}
	
	//Helper - Entity to Dto
	public AddressDto convertToAddressDto(Address address) {
		AddressDto addressDto = new AddressDto();
		addressDto.setId(address.getId());
		addressDto.setCity(address.getCity());
		addressDto.setUsState(address.getUsState());
		addressDto.setCountry(address.getCountry());
		addressDto.setPostalCode(address.getPostalCode());
		addressDto.setAddressType(address.getAddressType());
		addressDto.setAddress(address.getAddress());
//		addressDto.setEmployeeId(employee.getId());
		
		return addressDto;
	}
	
	//Helper - Dto to Entity
	public Address convertToAddress(AddressDto addressDto) {
		Employee employee = _employeeRepository.findById(addressDto.getEmployeeId())
				.orElseThrow(()-> new EntityNotFoundException("Parent not found"));
		Address address = new Address();
		//address.setId(addressDto.getId());
		address.setCity(addressDto.getCity());
		address.setUsState(addressDto.getUsState());
		address.setCountry(addressDto.getCountry());
		address.setPostalCode(addressDto.getPostalCode());
		address.setAddressType(addressDto.getAddressType());
		address.setAddress(addressDto.getAddress());
		address.setEmployeeId(employee);
		return address;
	}
}
