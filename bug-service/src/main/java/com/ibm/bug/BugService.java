package com.ibm.bug;

import java.util.HashMap;
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

	public List<Bug> getBugByPartialName(String bugName) {
		return bugRepository.findByNameIgnoreCase(bugName);
	}

	public List<Bug> getBugByName(String bugName) {
		return bugRepository.getByNameIgnoreCase(bugName);
	}

	public List<Bug> getBugByStatus(STATUS status) {
		return bugRepository.findByStatus(status);
	}
	
	public List<Bug> getBugByNameAndStatus(STATUS status, String bugName) {
		return bugRepository.findByStatusAndNameIgnoreCase(status, bugName);
	}
	
	public void deleteBug(String bugId) {
		bugRepository.deleteById(bugId);
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
		else if (oldStatus.equals(STATUS.NEW) && newStatus.equals(STATUS.NEW)) {
			valid = 1;
		}
		else if (oldStatus.equals(STATUS.ASSIGNED) && newStatus.equals(STATUS.ASSIGNED)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.OPEN) && newStatus.equals(STATUS.OPEN)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.FIXED) && newStatus.equals(STATUS.FIXED)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.PENDING_RETEST) && newStatus.equals(STATUS.PENDING_RETEST)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.RETEST) && newStatus.equals(STATUS.RETEST)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.REOPEN) && newStatus.equals(STATUS.REOPEN)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.VERIFIED) && newStatus.equals(STATUS.VERIFIED)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.WONT_FIX) && newStatus.equals(STATUS.WONT_FIX)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.REJECTED) && newStatus.equals(STATUS.REJECTED)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.DUPLICATE) && newStatus.equals(STATUS.DUPLICATE)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.DEFERRED) && newStatus.equals(STATUS.DEFERRED)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.NOT_A_BUG) && newStatus.equals(STATUS.NOT_A_BUG)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.NEED_MORE_INFO) && newStatus.equals(STATUS.NEED_MORE_INFO)) {
			valid = 1;
		}else if (oldStatus.equals(STATUS.COULDNOT_REPRODUCE) && newStatus.equals(STATUS.COULDNOT_REPRODUCE)) {
			valid = 1;
		}
		if (valid == 1) {
			bugRepository.save(bug);
		} else {
			throw new StatusIllegalArgumentException("Invalid status");
		}

	}
	
	public void updateBugNew(Bug bug) {
		STATUS status = bug.getStatus();
		Optional<Bug> oldBug = bugRepository.findById(bug.getId());
		oldBug.ifPresent(oldbug -> {
			STATUS oldstatus = oldbug.getStatus();
			HashMap<STATUS, STATUS[]> hmap = new HashMap<STATUS, STATUS[]>();
			hmap.put(STATUS.NEW, new STATUS[] { STATUS.NEW, STATUS.ASSIGNED });
			hmap.put(STATUS.ASSIGNED, new STATUS[] { STATUS.ASSIGNED,STATUS.OPEN, STATUS.DEFERRED, STATUS.DUPLICATE, STATUS.REJECTED, STATUS.NOT_A_BUG });
			hmap.put(STATUS.OPEN, new STATUS[] { STATUS.OPEN, STATUS.FIXED, STATUS.NEED_MORE_INFO, STATUS.COULDNOT_REPRODUCE, STATUS.WONT_FIX});
			hmap.put(STATUS.FIXED, new STATUS[] { STATUS.FIXED, STATUS.PENDING_RETEST });
			hmap.put(STATUS.PENDING_RETEST, new STATUS[] { STATUS.PENDING_RETEST, STATUS.RETEST });
			hmap.put(STATUS.RETEST, new STATUS[] { STATUS.RETEST, STATUS.VERIFIED, STATUS.REOPEN });
			hmap.put(STATUS.REOPEN, new STATUS[] { STATUS.REOPEN, STATUS.ASSIGNED });
			hmap.put(STATUS.VERIFIED, new STATUS[] { STATUS.VERIFIED, STATUS.CLOSED });
			hmap.put(STATUS.CLOSED, new STATUS[] { STATUS.CLOSED });
			hmap.put(STATUS.NEED_MORE_INFO, new STATUS[] { STATUS.NEED_MORE_INFO, STATUS.DEFERRED });
			hmap.put(STATUS.COULDNOT_REPRODUCE, new STATUS[] { STATUS.COULDNOT_REPRODUCE, STATUS.CLOSED });
			hmap.put(STATUS.WONT_FIX, new STATUS[] { STATUS.WONT_FIX, STATUS.CLOSED });
			hmap.put(STATUS.DEFERRED, new STATUS[] { STATUS.DEFERRED, STATUS.ASSIGNED });
			hmap.put(STATUS.NOT_A_BUG, new STATUS[] { STATUS.NOT_A_BUG, STATUS.CLOSED });
			hmap.put(STATUS.REJECTED, new STATUS[] { STATUS.REJECTED, STATUS.CLOSED });
			hmap.put(STATUS.DUPLICATE, new STATUS[] { STATUS.DUPLICATE, STATUS.CLOSED });
			
			int i = 0;
			String message = "-";
			for (i = 0; i < hmap.get(oldstatus).length; i++) {
				message += " ";
				if (status == hmap.get(oldstatus)[i]) {
					break;
				}
				message += hmap.get(oldstatus)[i];
			}
			if (hmap.get(oldstatus).length == i && !(oldstatus == status)) {
				throw new StatusIllegalArgumentException("STATUS CAN ONLY BE " + message);
			}
			bugRepository.save(bug);

		});

	}
	public BugRepository getBugRepository() {
		return bugRepository;
	}

	public void setBugRepository(BugRepository bugRepository) {
		this.bugRepository = bugRepository;
	}


}
