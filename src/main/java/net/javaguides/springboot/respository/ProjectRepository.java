package net.javaguides.springboot.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.javaguides.springboot.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
