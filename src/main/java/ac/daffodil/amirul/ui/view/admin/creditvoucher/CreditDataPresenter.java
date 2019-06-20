package ac.daffodil.amirul.ui.view.admin.creditvoucher;


import ac.daffodil.amirul.backend.data.entity.Credit;
import ac.daffodil.amirul.backend.service.CreditService;
import ac.daffodil.amirul.backend.service.LedgerService;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudPresenter4;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@ViewScope
public class CreditDataPresenter extends AbstractCrudPresenter4<Credit, CreditService, CreditFrontView> {
	//private AccountTypeService2 accountTypeService2;
	//private GroupService groupService;



	@Autowired
	public CreditDataPresenter(CreditDataProvider creditDataProvider, NavigationManager navigationManager,
                               CreditService service, BeanFactory beanFactory) {
		super(navigationManager, service, Credit.class, creditDataProvider, beanFactory);
	}

}
