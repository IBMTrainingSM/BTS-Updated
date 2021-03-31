package com.ibm.bug;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

	@Service
public class BugService {
	@Autowired
	BugRepository bugRepository;
	public String createBug(Bug bug) {
		Bug savedBug = bugRepository.save(bug);
		return savedBug.getId();
	}
	
	public List<Bug> getBugs() {

		return bugRepository.findAll();
	}

	public Optional<Bug> getBug(String bugId) {
		return bugRepository.findById(bugId);
	}
public void updateProject(@Valid Bug bug) {
		bugRepository.save(bug);
		
	}
	
}
