package com.ibm.bug;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BugRepository extends MongoRepository<Bug, String> {
	 List<Bug> findByNameIgnoreCase(String bugName);
	 List<Bug> findByStatus(STATUS status);
	 List<Bug> findByStatusAndNameIgnoreCase(STATUS status,String name);
}
