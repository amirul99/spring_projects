package ac.daffodil.amirul.backend;

import ac.daffodil.amirul.backend.data.entity.Credit;
import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.data.entity.Ledger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    Page<Credit> findByNarration(String voucherNo, Pageable page);

    int countByNarration(String voucherNo);
    String findByAccountToCredit(String credit);

    /*@Override
    List<Groups> findAll();*/

}
