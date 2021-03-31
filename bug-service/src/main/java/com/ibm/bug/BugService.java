package com.ibm.bug;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BugService {

	@Autowired
	BugRepository bugRepository;
	public void updateProject(@Valid Bug bug) {
		bugRepository.save(bug);
		
	}

}
