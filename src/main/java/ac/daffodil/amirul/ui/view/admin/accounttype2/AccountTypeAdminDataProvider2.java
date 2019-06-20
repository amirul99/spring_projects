package ac.daffodil.amirul.ui.view.admin.accounttype2;


import ac.daffodil.amirul.backend.data.entity.AccountType2;
import ac.daffodil.amirul.backend.service.AccountTypeService2;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;
import org.vaadin.spring.annotation.PrototypeScope;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
@PrototypeScope
public class AccountTypeAdminDataProvider2 extends FilterablePageableDataProvider<AccountType2, Object> {

	private final AccountTypeService2 accountTypeService2;

	@Autowired
	public AccountTypeAdminDataProvider2(AccountTypeService2 accountTypeService2) {
		this.accountTypeService2 = accountTypeService2;
	}

	@Override
	protected Page<AccountType2> fetchFromBackEnd(Query<AccountType2, Object> query, Pageable pageable) {
		return accountTypeService2.findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<AccountType2, Object> query) {
		return (int) accountTypeService2.countAnyMatching(getOptionalFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}

}