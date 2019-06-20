package ac.daffodil.amirul.ui.view.admin.debitvoucher;

import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.data.entity.Ledger2;
import ac.daffodil.amirul.backend.service.LedgerService;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.PageableDataProvider;

import java.util.ArrayList;
import java.util.List;


/**
 * A singleton data provider which knows which products are available.
 */
@SpringComponent
public class LedgerComboBoxDataProvider extends PageableDataProvider<Ledger, String> {

	private final LedgerService ledgerService;

	@Autowired
	public LedgerComboBoxDataProvider(LedgerService ledgerService) {
		this.ledgerService = ledgerService;
	}

	@Override
	protected Page<Ledger> fetchFromBackEnd(Query<Ledger, String> query, Pageable pageable) {
		return ledgerService.findAnyMatching(query.getFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Ledger, String> query) {
		return (int) ledgerService.countAnyMatching(query.getFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("ledgerName", SortDirection.ASCENDING));
		return sortOrders;
	}

}
