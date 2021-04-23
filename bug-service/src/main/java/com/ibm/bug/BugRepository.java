package com.ibm.bug;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BugRepository extends MongoRepository<Bug, String> {
	@Query("{name : {$regex : ?0,'$options':'i'}}")
	List<Bug> findByNameIgnoreCase(String bugName);

	List<Bug> getByNameIgnoreCase(String bugName);

	List<Bug> findByStatus(STATUS status);
	
	@Query("{status: ?0,name : {$regex : ?1,'$options':'i'}}")
	List<Bug> findByStatusAndNameIgnoreCase(STATUS status, String name);
}
