package in.codegram.ppmapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.codegram.ppmapi.domain.Backlog;
import in.codegram.ppmapi.domain.Project;
import in.codegram.ppmapi.exception.ProjectIDException;
import in.codegram.ppmapi.exception.ProjectIDExceptionResponse;
import in.codegram.ppmapi.repository.BacklogRepository;
import in.codegram.ppmapi.repository.ProjectRepository;
import in.codegram.ppmapi.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;

	@Override
	public Project saveOrUpdate(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			
			//TODO: Saving first time : When project is getting created first time, than backlog should be created along with that.
			if(project.getId()==null) {
				Backlog backlog=new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			//TODO : Updating project :  in case of updating of project backlog should not be null, same projectIdentifier should be set in backlog
			if(project.getId()!=null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIDException("Project Id "+project.getProjectIdentifier().toUpperCase()+" already exists");
		}
		
	}

	@Override
	public Project findProjectByProjectIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null) {
			throw new ProjectIDException("Project Id : "+projectId.toUpperCase()+" does not exists");
		}
		return project;
	}

	@Override
	public Iterable<Project> findAll() {
		
		return projectRepository.findAll();
	}

	@Override
	public void deleteProjectByIdentifier(String projectId) {
		Project project = findProjectByProjectIdentifier(projectId);
		projectRepository.delete(project);
		
	}
	
	
	

}