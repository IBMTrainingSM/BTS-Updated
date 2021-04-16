package com.ibm.bug;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BugRepository extends MongoRepository<Bug, String> {
	 Optional<Bug> findByName(String bugName);
}
