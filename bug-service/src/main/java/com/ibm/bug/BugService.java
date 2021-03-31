package com.ibm.bug;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugService {
	@Autowired
	BugRepository bugRepository;
	public List<Bug> getBugs() {

		return bugRepository.findAll();
	}

	public Optional<Bug> getBug(String bugId) {
		return bugRepository.findById(bugId);
	}
}
