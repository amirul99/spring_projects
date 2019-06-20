package ac.daffodil.amirul.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ac.daffodil.amirul.backend.data.entity.AccountType2;


public interface AccountTypeRepository3 extends JpaRepository<AccountType2, Long> {

	Page<AccountType2> findByNameLikeIgnoreCase(String name, Pageable page);

	int countByNameLikeIgnoreCase(String name);

}
