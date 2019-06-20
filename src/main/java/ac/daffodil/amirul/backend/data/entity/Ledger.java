package ac.daffodil.amirul.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="LedgerInfo")
public class Ledger extends AbstractEntity{

	@NotNull
	@Size(min = 1, max = 255)
	private String ledgerName;

	@NotNull
	@Size(min = 1, max = 255)
	private String ledgerCode;

	@NotNull
	@ManyToOne
	private Groups ledgerGroup;

	//@NotNull
	private double openingBalance;

	//@NotNull
	private double credit;

	//@NotNull
	private double debit;

	public Ledger() {

	}

	public Ledger(String ledgerName, String ledgerCode, Groups ledgerGroup, double openingBalance, double credit, double debit) {
		this.ledgerName = ledgerName;
		this.ledgerCode = ledgerCode;
		this.ledgerGroup = ledgerGroup;
		this.openingBalance = openingBalance;
		this.credit = credit;
		this.debit = debit;
	}

	/*public Ledger(String groupName, String groupCode, Groups groupAccount) {
		this.groupName = groupName;
		this.groupCode = groupCode;
		this.groupAccount = groupAccount;
	}*/

	public String getLedgerName() {
		return ledgerName;
	}

	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}

	public String getLedgerCode() {
		return ledgerCode;
	}

	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}

	public Groups getLedgerGroup() {
		return ledgerGroup;
	}

	public void setLedgerGroup(Groups ledgerGroup) {
		this.ledgerGroup = ledgerGroup;
	}

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	@Override
	public String toString() {
		return ledgerName;/*"Groups{" +
				"groupName='" + groupName + '\'' +
				'}';*/
	}
}
