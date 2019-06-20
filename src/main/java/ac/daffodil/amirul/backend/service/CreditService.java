package ac.daffodil.amirul.backend.service;

import ac.daffodil.amirul.backend.CreditRepository;
import ac.daffodil.amirul.backend.LedgerRepository;
import ac.daffodil.amirul.backend.data.entity.Credit;
import ac.daffodil.amirul.backend.data.entity.Ledger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class CreditService extends CrudService<Credit> {

	private final CreditRepository creditRepository;
	private final LedgerRepository ledgerRepository;

	@Autowired
	public CreditService(CreditRepository creditRepository, LedgerRepository ledgerRepository) {
		this.creditRepository = creditRepository;
		this.ledgerRepository = ledgerRepository;
	}

	@Override
	public Page<Credit> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByNarration(repositoryFilter, pageable);
		} else {
			return getRepository().findAll(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByNarration(repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	public Credit getDefault() {
		return findAnyMatching(Optional.empty(), new PageRequest(0, 1)).iterator().next();
	}

	@Override
	protected CreditRepository getRepository() {
		return creditRepository;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Credit saveOrder(Credit credit) {
		
		return creditRepository.save(credit);
	}

	@Transactional
	public void transferMoney( Ledger from,Ledger to, double amount) throws Exception {
		creditToAccount(to, amount);
		debitFromAccount(from, amount);
		
		ledgerRepository.saveAndFlush(to);
		ledgerRepository.saveAndFlush(from);
		ledgerRepository.flush();
		
		System.out.println(from.getOpeningBalance());
	}
	
	public void creditToAccount(Ledger to, double amount) throws Exception {
		//do straff
		double a,b;
		to.setCredit(to.getCredit() + amount);
		//a = to.getOpeningBalance() + amount;
		//to.setOpeningBalance(a);
		
		//throw new Exception("Error during credit");
	}
	
	public void debitFromAccount(Ledger from, double amount) throws Exception {
		//do staff and debited money from data base
		from.setDebit(from.getDebit() + amount);
		
		//double a;
		//a = from.getOpeningBalance()-amount;
		//from.setOpeningBalance(a);
		//throw new Exception("Error during credit");
		
	}
	

}