package ac.daffodil.amirul.ui.view.admin.ledger;

import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.service.LedgerService;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudPresenter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@ViewScope
public class LedgerPresenter extends AbstractCrudPresenter<Ledger, LedgerService, LedgerView> {
	//private AccountTypeService2 accountTypeService2;
	//private GroupService groupService;



	@Autowired
	public LedgerPresenter(LedgerDataProvider groupAdminDataProvider, NavigationManager navigationManager,
						   LedgerService service, BeanFactory beanFactory) {
		super(navigationManager, service, Ledger.class, groupAdminDataProvider, beanFactory);
	}

}
