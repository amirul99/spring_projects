package ac.daffodil.amirul.backend;

import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitRepository2 extends JpaRepository<Debit, Long> {
    Page<Debit> findByNarration(String voucherNo, Pageable page);

    int countByNarration(String voucherNo);    
    String findByAccountToDebit(String debit);
    /*@Override
    List<Groups> findAll();*/

}
