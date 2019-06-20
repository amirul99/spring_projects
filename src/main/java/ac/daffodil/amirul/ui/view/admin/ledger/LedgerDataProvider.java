package ac.daffodil.amirul.ui.view.admin.ledger;


import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.service.LedgerService;
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
public class LedgerDataProvider extends FilterablePageableDataProvider<Ledger, Object> {

	private final LedgerService groupService;

	@Autowired
	public LedgerDataProvider(LedgerService groupService) {
		this.groupService = groupService;
	}

	@Override
	protected Page<Ledger> fetchFromBackEnd(Query<Ledger, Object> query, Pageable pageable) {
		return groupService.findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Ledger, Object> query) {
		return (int) groupService.countAnyMatching(getOptionalFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("ledgerName", SortDirection.ASCENDING));
		return sortOrders;
	}

}