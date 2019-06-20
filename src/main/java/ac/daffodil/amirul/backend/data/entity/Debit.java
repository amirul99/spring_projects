package ac.daffodil.amirul.backend.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "DebitInfo")
public class Debit extends AbstractEntity{

    @NotNull
    private Date date;

    @NotNull
    @Size(max = 255)
    private String voucherNo;

    @NotNull
    @ManyToOne
    private Ledger accountToDebit;

    @NotNull
    @Min(0)
    @Max(10000000)
    private double debitAmount;

    @NotNull
    @ManyToOne
    private Ledger accountToCredit;

    @NotNull
    @Min(0)
    @Max(10000000)
    private double creditAmount;

    @Size(max = 255)
    private String narration;


    public Debit() {

    }

    public Debit(Date date, String voucherNo, Ledger accountToDebit, double debitAmount, Ledger accountToCredit, double creditAmount, String narration) {
        this.date = date;
        this.voucherNo = voucherNo;
        this.accountToDebit = accountToDebit;
        this.debitAmount = debitAmount;
        this.accountToCredit = accountToCredit;
        this.creditAmount = creditAmount;
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

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    @Override
    public String toString() {
        return
                "voucherNo='" + voucherNo + '\'' ;
    }

    /*public void debit(double amount) {


    }

    public void credit(double amount) {

    }*/


}
