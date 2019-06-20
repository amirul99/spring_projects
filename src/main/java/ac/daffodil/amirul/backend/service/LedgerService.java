package ac.daffodil.amirul.backend.service;

import ac.daffodil.amirul.backend.LedgerRepository;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LedgerService extends CrudService<Ledger> {

	private final LedgerRepository groupRepository;

	@Autowired
	public LedgerService(LedgerRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public Page<Ledger> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByLedgerNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return getRepository().findAll(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByLedgerNameLikeIgnoreCase(repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	public Ledger getDefault() {
		return findAnyMatching(Optional.empty(), new PageRequest(0, 1)).iterator().next();
	}

	@Override
	protected LedgerRepository getRepository() {
		return groupRepository;
	}

}