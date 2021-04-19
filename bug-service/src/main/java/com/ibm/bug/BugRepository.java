package com.ibm.bug;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BugRepository extends MongoRepository<Bug, String> {
	 List<Bug> findByName(String bugName);
}
