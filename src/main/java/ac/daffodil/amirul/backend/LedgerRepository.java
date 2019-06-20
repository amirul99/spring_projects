package ac.daffodil.amirul.backend;

import ac.daffodil.amirul.backend.data.entity.Ledger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.google.gwt.validation.client.impl.Group;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    Page<Ledger> findByLedgerNameLikeIgnoreCase(String ledgerName, Pageable page);

    int countByLedgerNameLikeIgnoreCase(String ledgerName);
    //List<Ledger> findAllByOrderByRDateAsc();
    List<Ledger> findAllByLedgerGroup(String group);
    
    
    /*@Override
    List<Groups> findAll();*/

}
