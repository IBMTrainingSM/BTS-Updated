package com.ibm.bug;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BugController {

	@Autowired
	BugService bugService;

	@PostMapping("/bug")
	@ResponseStatus(code = HttpStatus.CREATED)
	String createBug(@RequestBody @Valid Bug bug, BindingResult bindingResult) {
		validateModel(bindingResult);
		return bugService.createBug(bug);
	}

	private void validateModel(Errors bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Something went wrong. Please retry");
		}
	}

	@GetMapping("/bug")
	List<Bug> getBugs() {
		return bugService.getBugs();
	}

	@GetMapping("/bug/{id}")
	Optional<Bug> getBug(@PathVariable("id") String bugId) {
		return bugService.getBug(bugId);
	}
	
	@GetMapping("/bug/name/{name}")
	List<Bug> getBugByName(@PathVariable("name") String bugName) {
		return bugService.getBugByName(bugName);
	}
	
	@GetMapping("/bug/status/{status}")
	List<Bug> getBugByStatus(@PathVariable("status") STATUS status) {
		return bugService.getBugByStatus(status);
	}
	
	@GetMapping("/bug/search/{name}")
	List<Bug> getBugByNameAndStatus(@PathParam("status") STATUS status,@PathVariable("name") String bugName) {
		return bugService.getBugByNameAndStatus(status,bugName);
	}
	
	@PutMapping("/bug/{id}")
	void updateBug(@RequestBody @Valid Bug bug, BindingResult bindingResult, @PathVariable("id") String bugId) {
		validateModel(bindingResult);
		bug.setId(bugId);
		bugService.updateBug(bug);
	}
	
	@DeleteMapping("/bug/{id}")
	void deleteBug(@PathVariable("id") String bugId) {
		bugService.deleteBug(bugId);
	}

}
