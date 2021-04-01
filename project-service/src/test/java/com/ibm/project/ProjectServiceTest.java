package com.ibm.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProjectServiceTest {

	@Test
	void testCreateProject() {
		ProjectService projectService = new ProjectService();
		ProjectRepository dummyRepo = new DummyProjectRepository();
		projectService.setProjectRepository(dummyRepo);
		Project project = new Project();
		String projectId = projectService.createProject(project);
		assertNotNull(projectId);
	}

//	@Test
//	void testGetProjects() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void testGetProject() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//	void testUpdateProject() {
//		fail("Not yet implemented");
//	}
}
