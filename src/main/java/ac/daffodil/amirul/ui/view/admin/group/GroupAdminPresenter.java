package ac.daffodil.amirul.ui.view.admin.group;

import ac.daffodil.amirul.backend.data.entity.Groups;
import ac.daffodil.amirul.backend.service.GroupService;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudPresenter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@ViewScope
public class GroupAdminPresenter extends AbstractCrudPresenter<Groups, GroupService, GroupAdminView> {
	//private AccountTypeService2 accountTypeService2;
	//private GroupService groupService;



	@Autowired
	public GroupAdminPresenter(GroupAdminDataProvider groupAdminDataProvider, NavigationManager navigationManager,
							   GroupService service, BeanFactory beanFactory) {
		super(navigationManager, service, Groups.class, groupAdminDataProvider, beanFactory);
	}

}
