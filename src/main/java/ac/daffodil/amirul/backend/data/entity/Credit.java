package ac.daffodil.amirul.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "CreditInfo")
public class Credit extends AbstractEntity{

    @NotNull
    private Date date;

    @Size(max = 255)
    private String voucherNo;

    @NotNull
    @ManyToOne
    private Ledger accountToCredit;

    @NotNull
    @Min(0)
    @Max(10000000)
    private double creditAmount;
    
    @NotNull
    @ManyToOne
    private Ledger accountToDebit;

    @NotNull
    @Min(0)
    @Max(10000000)
    private double debitAmount;

    @Size(max = 255)
    private String narration;


    public Credit() {

    }

    public Credit(Date date, String voucherNo, Ledger accountToCredit, double creditAmount, Ledger accountToDebit,
			double debitAmount, String narration) {
		super();
		this.date = date;
		this.voucherNo = voucherNo;
		this.accountToCredit = accountToCredit;
		this.creditAmount = creditAmount;
		this.accountToDebit = accountToDebit;
		this.debitAmount = debitAmount;
		this.narration = narration;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Ledger getAccountToCredit() {
		return accountToCredit;
	}

	public void setAccountToCredit(Ledger accountToCredit) {
		this.accountToCredit = accountToCredit;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public Ledger getAccountToDebit() {
		return accountToDebit;
	}

	public void setAccountToDebit(Ledger accountToDebit) {
		this.accountToDebit = accountToDebit;
	}

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}
	
	
    
}
