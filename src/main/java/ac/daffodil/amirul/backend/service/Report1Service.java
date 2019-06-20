package ac.daffodil.amirul.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import ac.daffodil.amirul.backend.CreditRepository;
import ac.daffodil.amirul.backend.DebitRepository2;
import ac.daffodil.amirul.backend.GroupRepository;
import ac.daffodil.amirul.backend.LedgerRepository;
import ac.daffodil.amirul.backend.Report1Repository;
import ac.daffodil.amirul.backend.data.entity.Credit;
import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.data.entity.Groups;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.data.entity.Report1;

@Service
public class Report1Service {
	
	private final LedgerRepository ledgerRepository;
	private final DebitRepository2 debitRepository;
	private final CreditRepository creditRepository;
	private final Report1Repository report1Repository;
	private final GroupRepository groupRepository;

	@Autowired
	public Report1Service(LedgerRepository ledgerRepository,DebitRepository2 debitRepository, 
			CreditRepository creditRepository,Report1Repository report1Repository,GroupRepository groupRepository) {
		this.ledgerRepository = ledgerRepository;
		this.debitRepository = debitRepository;
		this.creditRepository = creditRepository;
		this.report1Repository = report1Repository;
		this.groupRepository = groupRepository;
		
	}
	
	protected Report1Repository getRepository() {
		return report1Repository;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Transactional
	public void getDetails(Ledger ledger) throws Exception{
		//report1Repository.deleteAllInBatch();
		
		//System.out.println(report1Repository.count());
		
		ArrayList<Debit> debitList= new ArrayList<Debit>();
		ArrayList<Credit> creditList= new ArrayList<Credit>();
		debitList.addAll(debitRepository.findAll());
		creditList.addAll(creditRepository.findAll());
		
		//System.out.println(debitList.size());
		//System.out.println(creditList.size());
		
		
		for(int i = 0; i < creditRepository.count(); i++ ) {
			if (ledger.getLedgerName().equals(creditList.get(i).getAccountToCredit().toString())) {
				Report1 report1= new Report1();
				report1.setrDate(creditList.get(i).getDate());
				report1.setrDebit(0);
				report1.setrCredit(creditList.get(i).getCreditAmount());
				report1Repository.save(report1);
				//report1Repository.saveAndFlush(report1);
			}
			else if (ledger.getLedgerName().equals(creditList.get(i).getAccountToDebit().toString())) {
				Report1 report1= new Report1();
				report1.setrDate(creditList.get(i).getDate());
				report1.setrDebit(creditList.get(i).getDebitAmount());
				report1.setrCredit(0);
				report1Repository.save(report1);
				//report1Repository.saveAndFlush(report1);
			}
		}
		
		for(int i = 0; i < debitRepository.count(); i++ ) {
			
			if (ledger.getLedgerName().equals(debitList.get(i).getAccountToDebit().toString())) {
				Report1 report1= new Report1();
				report1.setrDate(debitList.get(i).getDate());
				report1.setrDebit(debitList.get(i).getDebitAmount());
				report1.setrCredit(0);
				report1Repository.save(report1);
			}
			else if (ledger.getLedgerName().equals(debitList.get(i).getAccountToCredit().toString())) {
				Report1 report1= new Report1();
				report1.setrDate(debitList.get(i).getDate());
				report1.setrDebit(0);
				report1.setrCredit(debitList.get(i).getCreditAmount());
				report1Repository.save(report1);
			}
			else {
				System.out.println("no");
			}
		}
		
		
		
		
	
	}
	
	
	public void getProfitLoss() {
		//Groups group =new Groups();
		float income,expense;
		income = 0;
		expense = 0;
		ArrayList<Ledger> incomeLedgerList= new ArrayList<Ledger>();
		incomeLedgerList.addAll(ledgerRepository.findAllByLedgerGroup("Current Assets"));
		
		for (Ledger ledger : incomeLedgerList) {
			income+=ledger.getDebit();
		}
		System.out.println(income);
		
		ArrayList<Ledger> expenseLedgerList= new ArrayList<Ledger>();
		expenseLedgerList.addAll(ledgerRepository.findAllByLedgerGroup("Current Assets"));
		for (Ledger ledger : expenseLedgerList) {
			expense+=ledger.getDebit();
		}
		System.out.println(expense);
		
		
	}
	
	
	public List<Map<String, Object>> report(){
		//ArrayList<Ledger> incomeLedgerList= new ArrayList<Ledger>();
		List<Map<String, Object>> incomeLedgerList = new ArrayList<Map<String, Object>>();
		
		
		for (int i = 1; i <= ledgerRepository.count(); i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			
			
			if(ledgerRepository.findOne((long) i).getLedgerGroup().getGroupAccount().getName().equals("Assets")) {
				
				item.put("ledgerName", ledgerRepository.findOne((long) i).getLedgerName());
				item.put("amount", ledgerRepository.findOne((long) i).getDebit());
				incomeLedgerList.add(item);
				//System.out.println(item + " --item");
				//System.out.println(ledgerRepository.findOne((long) i).getDebit());
				//System.out.println(ledgerRepository.findOne((long) i).getLedgerGroup().getGroupAccount().getName());
			}
		}
		System.out.println(incomeLedgerList);
		return incomeLedgerList;
		
		
		
		
		/*incomeLedgerList.addAll(ledgerRepository.findAllByLedgerGroup("Current Assets"));
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Ledger ledger : incomeLedgerList) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("ledgerName", ledger.getLedgerName());
			item.put("amount", ledger.getDebit());
			result.add(item);
		}
		return result;*/
	}
	
	}