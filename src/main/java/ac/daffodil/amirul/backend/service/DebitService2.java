package ac.daffodil.amirul.backend.service;

import ac.daffodil.amirul.backend.DebitRepository2;
import ac.daffodil.amirul.backend.LedgerRepository;
import ac.daffodil.amirul.backend.data.OrderState;
import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.data.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DebitService2 extends CrudService<Debit> {

	private final DebitRepository2 debitRepository2;
	private final LedgerRepository ledgerRepository;

	@Autowired
	public DebitService2(DebitRepository2 debitRepository2, LedgerRepository ledgerRepository) {
		this.debitRepository2 = debitRepository2;
		this.ledgerRepository = ledgerRepository;
	}

	@Override
	public Page<Debit> findAnyMatching(Optional<String> filter, Pageable pageable) {
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

	public Debit getDefault() {
		return findAnyMatching(Optional.empty(), new PageRequest(0, 1)).iterator().next();
	}

	@Override
	protected DebitRepository2 getRepository() {
		return debitRepository2;
	}
	
	@Transactional(rollbackOn = Exception.class)
	public Debit saveOrder(Debit debit) {
		
		return debitRepository2.save(debit);
	}

	@Transactional
	public void transferMoney( Ledger from,Ledger to, double amount) throws Exception {
		debitFromAccount(from, amount);
		creditToAccount(to, amount);
		ledgerRepository.saveAndFlush(from);
		ledgerRepository.saveAndFlush(to);
		
		
		System.out.println(from.getOpeningBalance());
	}
	public void debitFromAccount(Ledger from, double amount) throws Exception {
		//do staff and debited money from data base
		double a,b;
		//a = from.getOpeningBalance()-amount;
		b = from.getDebit() + amount;
		//b =a-amount;
		//from.setOpeningBalance(a);
		from.setDebit(b);
		//throw new Exception("Error during debit");
		
	}
	public void creditToAccount(Ledger to, double amount) throws Exception {
		//do straff
		double a,b;
		//a = to.getOpeningBalance() + amount;
		b = to.getCredit() + amount;
		
		//to.setOpeningBalance(a);
		to.setCredit(b);
		//b = a + amount;
		//throw new Exception("Error during credit");
	}

}