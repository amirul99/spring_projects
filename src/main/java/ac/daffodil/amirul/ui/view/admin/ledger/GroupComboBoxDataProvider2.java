package ac.daffodil.amirul.ui.view.admin.ledger;


import ac.daffodil.amirul.backend.data.entity.Groups;
import ac.daffodil.amirul.backend.service.GroupService;
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
public class GroupComboBoxDataProvider2 extends PageableDataProvider<Groups, String> {

	private final GroupService groupService;

	@Autowired
	public GroupComboBoxDataProvider2(GroupService groupService) {
		this.groupService = groupService;
	}

	@Override
	protected Page<Groups> fetchFromBackEnd(Query<Groups, String> query, Pageable pageable) {
		return groupService.findAnyMatching(query.getFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Groups, String> query) {
		return (int) groupService.countAnyMatching(query.getFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("groupName", SortDirection.ASCENDING));
		return sortOrders;
	}

}
