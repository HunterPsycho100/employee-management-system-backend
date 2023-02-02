package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private long id;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "us_state")
	private String usState;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "address_type")
	private String addressType;
	
	@Column(name = "address1")
	private String address;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	//@JsonIgnore
	private Employee employees;
	
	public Address() {
	}
	
	public Address(String city, String usState, String country, String postalCode, String addressType, String address,
			Employee employees) {
		super();
		this.city = city;
		this.usState = usState;
		this.country = country;
		this.postalCode = postalCode;
		this.addressType = addressType;
		this.address = address;
		this.employees = employees;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUsState() {
		return usState;
	}
	public void setUsState(String usState) {
		this.usState = usState;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Employee getEmployeeId() {
		return employees;
	}
	public void setEmployeeId(Employee employee) {
		this.employees = employee;
	}


}
