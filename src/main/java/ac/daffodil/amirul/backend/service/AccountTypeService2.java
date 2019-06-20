package ac.daffodil.amirul.backend.service;

import java.util.ArrayList;
import java.util.Optional;

import ac.daffodil.amirul.backend.AccountTypeRepository2;
import ac.daffodil.amirul.backend.data.entity.AccountType2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AccountTypeService2 extends CrudService<AccountType2> {

    private final AccountTypeRepository2 accountTypeRepository2;

    @Autowired
    public AccountTypeService2(AccountTypeRepository2 accountTypeRepository2) {
        this.accountTypeRepository2 = accountTypeRepository2;
    }

    @Override
    public Page<AccountType2> findAnyMatching(Optional<String> filter, Pageable pageable) {
        if (filter.isPresent()) {
            String repositoryFilter = "%" + filter.get() + "%";
            return getRepository().findByNameLikeIgnoreCase(repositoryFilter, pageable);
        } else {
            return getRepository().findAll(pageable);
        }
    }

    @Override
    public long countAnyMatching(Optional<String> filter) {
        if (filter.isPresent()) {
            String repositoryFilter = "%" + filter.get() + "%";
            return getRepository().countByNameLikeIgnoreCase(repositoryFilter);
        } else {
            return getRepository().count();
        }
    }

    public AccountType2 getDefault() {
        return findAnyMatching(Optional.empty(), new PageRequest(0, 1)).iterator().next();
    }

    /*public ArrayList allAccountType(){
        ArrayList list = new ArrayList();
        list.addAll(getRepository().getAllByName());
        return list;
    }*/

    @Override
    protected AccountTypeRepository2 getRepository() {
        return accountTypeRepository2;
    }

}
