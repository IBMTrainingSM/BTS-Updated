package com.ibm.bug;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BugServiceTest {

	@Test
	void testCreateBug() {
		BugService bugService = new BugService();
		BugRepository dummyRepo = new DummyBugRepository();
		bugService.setBugRepository(dummyRepo);
		Bug bug = new Bug();
		String bugId = bugService.createBug(bug);
		assertNotNull(bugId);
	}

}
