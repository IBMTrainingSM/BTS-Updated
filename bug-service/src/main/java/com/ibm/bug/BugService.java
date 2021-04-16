package com.ibm.bug;

import java.util.List;
import java.util.Optional;

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

	public Optional<Bug> getBugByName(String bugName) {
		return bugRepository.findByName(bugName);
	}

	public void updateBug(Bug bug) {
		int valid = 0;
		String bugId = bug.getId();
		STATUS newStatus = bug.getStatus();
		STATUS oldStatus = STATUS.NEW;
		List<Bug> bugs = bugRepository.findAll();
		for (Bug eachBug : bugs) {
			if (bugId.equals(eachBug.getId())) {
				oldStatus = eachBug.getStatus();
			}
		}
		if (newStatus.equals(STATUS.ASSIGNED) && (oldStatus.equals(STATUS.NEW) || oldStatus.equals(STATUS.REOPEN)
				|| oldStatus.equals(STATUS.DEFERRED))) {
			valid = 1;
		} else if (oldStatus.equals(STATUS.ASSIGNED) && (newStatus.equals(STATUS.OPEN)
				|| newStatus.equals(STATUS.DEFERRED) || newStatus.equals(STATUS.DUPLICATE)
				|| newStatus.equals(STATUS.REJECTED) || newStatus.equals(STATUS.NOT_A_BUG))) {
			valid = 1;
		} else if (oldStatus.equals(STATUS.OPEN)
				&& (newStatus.equals(STATUS.FIXED) || newStatus.equals(STATUS.NEED_MORE_INFO)
						|| newStatus.equals(STATUS.COULDNOT_REPRODUCE) || newStatus.equals(STATUS.WONT_FIX))) {
			valid = 1;
		} else if (oldStatus.equals(STATUS.FIXED) && newStatus.equals(STATUS.PENDING_RETEST)) {
			valid = 1;
		} else if (oldStatus.equals(STATUS.PENDING_RETEST) && newStatus.equals(STATUS.RETEST)) {
			valid = 1;
		} else if (oldStatus.equals(STATUS.RETEST)
				&& (newStatus.equals(STATUS.VERIFIED) || newStatus.equals(STATUS.REOPEN))) {
			valid = 1;
		} else if (oldStatus.equals(STATUS.VERIFIED) && newStatus.equals(STATUS.CLOSED)) {
			valid = 1;
		} else if (oldStatus.equals(STATUS.NEED_MORE_INFO) && newStatus.equals(STATUS.DEFERRED)) {
			valid = 1;
		} else if (newStatus.equals(STATUS.CLOSED) && (oldStatus.equals(STATUS.COULDNOT_REPRODUCE)
				|| oldStatus.equals(STATUS.WONT_FIX) || oldStatus.equals(STATUS.DUPLICATE)
				|| oldStatus.equals(STATUS.REJECTED) || oldStatus.equals(STATUS.NOT_A_BUG))) {
			valid = 1;
		} else if (oldStatus.equals(STATUS.CLOSED) && newStatus.equals(STATUS.CLOSED)) {
			valid = 1;
		}
		if (valid == 1) {
			bugRepository.save(bug);
		} else {
			throw new IllegalArgumentException("Invalid status");
		}

	}

	public BugRepository getBugRepository() {
		return bugRepository;
	}

	public void setBugRepository(BugRepository bugRepository) {
		this.bugRepository = bugRepository;
	}

}
