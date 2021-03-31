package com.ibm.bug;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class Bugcontroller {
	@Autowired
	BugService bugService;
	@GetMapping("/bug")
	List<Bug> getBug() {
		return bugService.getBugs();
	}

	@GetMapping("/bug/{id}")
	Optional<Bug> getBug(@PathVariable("id") String bugId) {
		return bugService.getBug(bugId);
	}


}
