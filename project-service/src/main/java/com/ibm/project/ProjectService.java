package com.ibm.project;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProjectService {
	@Autowired
	ProjectRepository projectRepository;
	@Transactional
	public String createProject(@RequestBody Project project) {
		Project projectsave = projectRepository.save(project);
		if(project != null)
			throw new RuntimeException();
		return projectsave.getId();
	}

	public List<Project> getProjects() {

		return projectRepository.findAll();
	}

	public Optional<Project> getProject(String projectId) {
		return projectRepository.findById(projectId);
	}

	public void updateProject(Project project) {
		projectRepository.save(project);

	}

}
