package ac.daffodil.amirul.ui.view.admin.group;

import java.util.ArrayList;
import java.util.List;

import ac.daffodil.amirul.backend.data.entity.AccountType2;
import ac.daffodil.amirul.backend.service.AccountTypeService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;


/**
 * A singleton data provider which knows which products are available.
 */
@SpringComponent
public class AccountTypeComboBoxDataProvider extends PageableDataProvider<AccountType2, String> {

	private final AccountTypeService2 accountTypeService2;

	@Autowired
	public AccountTypeComboBoxDataProvider(AccountTypeService2 accountTypeService2) {
		this.accountTypeService2 = accountTypeService2;
	}

	@Override
	protected Page<AccountType2> fetchFromBackEnd(Query<AccountType2, String> query, Pageable pageable) {
		return accountTypeService2.findAnyMatching(query.getFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<AccountType2, String> query) {
		return (int) accountTypeService2.countAnyMatching(query.getFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("name", SortDirection.ASCENDING));
		return sortOrders;
	}

}
