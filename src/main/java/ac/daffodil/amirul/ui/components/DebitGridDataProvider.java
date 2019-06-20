package ac.daffodil.amirul.ui.components;

import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.service.DebitService2;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;
import org.vaadin.spring.annotation.PrototypeScope;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringComponent
@PrototypeScope
public class DebitGridDataProvider extends FilterablePageableDataProvider<Debit, Object> {

	private final DebitService2 orderService;
	private LocalDate filterDate = LocalDate.now().minusDays(1);

	@Autowired
	public DebitGridDataProvider(DebitService2 orderService) {
		this.orderService = orderService;
	}

	@Override
	protected Page<Debit> fetchFromBackEnd(Query<Debit, Object> query, Pageable pageable) {
		return orderService.findAnyMatching(getOptionalFilter(), pageable);
	}

	private Optional<LocalDate> getOptionalFilterDate() {
		if (filterDate == null) {
			return Optional.empty();
		} else {
			return Optional.of(filterDate);
		}
	}

	public void setIncludePast(boolean includePast) {
		if (includePast) {
			filterDate = null;
		} else {
			filterDate = LocalDate.now().minusDays(1);
		}
	}

	@Override
	protected int sizeInBackEnd(Query<Debit, Object> query) {
		return (int) orderService.countAnyMatching(getOptionalFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("date", SortDirection.ASCENDING));
		//sortOrders.add(new QuerySortOrder("voucherNo", SortDirection.ASCENDING));
		// id included only to always get a stable sort order
		sortOrders.add(new QuerySortOrder("id", SortDirection.DESCENDING));
		return sortOrders;
	}
}
