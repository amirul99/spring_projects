package ac.daffodil.amirul.ui.view.admin.debitvoucher;

import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.service.DebitService2;
import ac.daffodil.amirul.backend.service.LedgerService;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudPresenter;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudPresenter2;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerDataProvider;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerView;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@ViewScope
public class DebitDataPresenter2 extends AbstractCrudPresenter2<Debit, DebitService2, DebitFrontView2> {
	//private AccountTypeService2 accountTypeService2;
	//private GroupService groupService;



	@Autowired
	public DebitDataPresenter2(DebitDataProvider2 groupAdminDataProvider, NavigationManager navigationManager,
                               DebitService2 service, BeanFactory beanFactory) {
		super(navigationManager, service, Debit.class, groupAdminDataProvider, beanFactory);
	}

}
