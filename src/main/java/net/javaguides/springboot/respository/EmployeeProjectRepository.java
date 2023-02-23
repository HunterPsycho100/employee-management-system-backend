package net.javaguides.springboot.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.EmployeeProject;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long>{

}
