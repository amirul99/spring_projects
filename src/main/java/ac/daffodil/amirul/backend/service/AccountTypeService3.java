package ac.daffodil.amirul.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ac.daffodil.amirul.backend.AccountTypeRepository3;
import ac.daffodil.amirul.backend.data.entity.AccountType2;


@Service
public class AccountTypeService3 extends CrudService<AccountType2> {

	private final AccountTypeRepository3 accountTypeRepository3;

	@Autowired
	public AccountTypeService3(AccountTypeRepository3 accountTypeRepository3) {
		this.accountTypeRepository3 = accountTypeRepository3;
	}

	@Override
	public Page<AccountType2> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return getRepository().findAll(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByNameLikeIgnoreCase(repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	@Override
	protected AccountTypeRepository3 getRepository() {
		return accountTypeRepository3;
	}

}
