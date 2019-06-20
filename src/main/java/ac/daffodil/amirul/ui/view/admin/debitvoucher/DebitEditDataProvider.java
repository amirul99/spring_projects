package ac.daffodil.amirul.ui.view.admin.debitvoucher;


import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.service.DebitService2;
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
public class DebitEditDataProvider extends FilterablePageableDataProvider<Debit, Object> {

	private final DebitService2 groupService;

	@Autowired
	public DebitEditDataProvider(DebitService2 groupService) {
		this.groupService = groupService;
	}

	@Override
	protected Page<Debit> fetchFromBackEnd(Query<Debit, Object> query, Pageable pageable) {
		return groupService.findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Debit, Object> query) {
		return (int) groupService.countAnyMatching(getOptionalFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("ledgerName", SortDirection.ASCENDING));
		return sortOrders;
	}

}