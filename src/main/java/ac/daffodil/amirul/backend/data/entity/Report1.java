package ac.daffodil.amirul.backend.data.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="Report1")
public class Report1 extends AbstractEntity{
	@NotNull
	
	private Date rDate;	
	
	private double rDebit;
	private double rCredit;
	
	public Report1() {
		
	}

	public Report1(Date rDate, double rDebit, double rCredit) {		
		this.rDate = rDate;
		this.rDebit = rDebit;
		this.rCredit = rCredit;
	}

	public Date getrDate() {
		return rDate;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

	public double getrDebit() {
		return rDebit;
	}

	public void setrDebit(double rDebit) {
		this.rDebit = rDebit;
	}

	public double getrCredit() {
		return rCredit;
	}

	public void setrCredit(double rCredit) {
		this.rCredit = rCredit;
	}

	@Override
	public String toString() {
		return "Report1 [rDate=" + rDate + ", rDebit=" + rDebit + ", rCredit=" + rCredit + "]";
	}
	
	
	
	
	
	

}
