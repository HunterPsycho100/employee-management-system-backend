package net.javaguides.springboot;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.respository.EmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTests {

		@Autowired
		private EmployeeRepository employeeRepository;
		
		// Junit test for save employee
		@Test
		public void saveEmployeeTest() {
			
			System.out.println("this test ran");
			
			Employee employee = new Employee();
				employee.setFirstName("Jon");
				employee.setLastName("Doe");
				employee.setEmailId("jd@gmail.com");
				employee.setAddresses(null);
			
			
		employeeRepository.save(employee);

		Assertions.assertThat(employee.getId()).isGreaterThan(0);
		}
		
		@Test
		public void getEmployeeTest() {
			Employee employee = employeeRepository.findById(1L).get();
			
			Assertions.assertThat(employee.getId()).isEqualTo(1L);
		}
		
		@Test
		public void getListOfEmployeeTest() {
			List<Employee> employees = employeeRepository.findAll();
			
			Assertions.assertThat(employees.size()).isGreaterThan(0);
		}
		
		@Test
		public void updateEmployeeTest() {
			Employee employee = employeeRepository.findById(1L).get();
			
			employee.setEmailId("hwhanay@yahoo.com");
			Employee employeeUpdated = employeeRepository.save(employee);
			
			Assertions.assertThat(employeeUpdated.getEmailId()).isEqualTo("jd@yahoo.com");
		}
		
//		@Test
//		public void deleteEmployeeTest() {
//			Employee employee = employeeRepository.findById(1L).get();
//			
//			employeeRepository.delete(employee);
//			
//			Employee employee1 = null;
//			Optional<Employee> optionalEmployee = employeeRepository.findByEmail("hwhanay@yahoo.com");
//			
//			if(optionalEmployee.isPresent()) {
//				employee1 = optionalEmployee.get();
//			}
//			
//			Assertions.assertThat(employee1).isNull();
//		}	
		
		
}
