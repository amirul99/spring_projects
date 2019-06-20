package ac.daffodil.amirul.backend.data.entity;

import javax.persistence.Entity;



@Entity(name="Report2")
public class Report2 extends AbstractEntity{
		
	private String ledgerName;		
	private double amount;	
	
	public Report2() {		
	}

	public Report2(String ledgerName, double amount) {		
		this.ledgerName = ledgerName;
		this.amount = amount;
	}

	public String getLedgerName() {
		return ledgerName;
	}

	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	

	
	
	
	
	
	
	

}
