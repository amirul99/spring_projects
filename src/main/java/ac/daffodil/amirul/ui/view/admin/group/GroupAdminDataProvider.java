package ac.daffodil.amirul.ui.view.admin.group;


import ac.daffodil.amirul.backend.data.entity.Groups;
import ac.daffodil.amirul.backend.service.GroupService;
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
public class GroupAdminDataProvider extends FilterablePageableDataProvider<Groups, Object> {

	private final GroupService groupService;

	@Autowired
	public GroupAdminDataProvider(GroupService groupService) {
		this.groupService = groupService;
	}

	@Override
	protected Page<Groups> fetchFromBackEnd(Query<Groups, Object> query, Pageable pageable) {
		return groupService.findAnyMatching(getOptionalFilter(), pageable);
	}

	@Override
	protected int sizeInBackEnd(Query<Groups, Object> query) {
		return (int) groupService.countAnyMatching(getOptionalFilter());
	}

	@Override
	protected List<QuerySortOrder> getDefaultSortOrders() {
		List<QuerySortOrder> sortOrders = new ArrayList<>();
		sortOrders.add(new QuerySortOrder("groupName", SortDirection.ASCENDING));
		return sortOrders;
	}

}