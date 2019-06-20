package ac.daffodil.amirul.ui.view.admin.creditvoucher;


import ac.daffodil.amirul.backend.data.entity.Credit;
import ac.daffodil.amirul.backend.service.CreditService;
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
public class CreditDataProvider extends FilterablePageableDataProvider<Credit, Object> {

	private final CreditService groupService;

	@Autowired
	public CreditDataProvider(CreditService groupService) {
		this.groupService = groupService;
	}

	@Override
	protected Page<Credit> fetchFromBackEnd(Query<Credit, Object> query, Pageable pageable) {
		return groupService.findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Credit, Object> query) {
		return (int) groupService.countAnyMatching(getOptionalFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("voucherNo", SortDirection.ASCENDING));
		return sortOrders;
	}

}