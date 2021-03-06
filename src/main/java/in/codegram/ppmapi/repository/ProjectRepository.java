package in.codegram.ppmapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.codegram.ppmapi.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	//TODO : No need to implement methods
	Project findByProjectIdentifier(String projectIdentifier);

}