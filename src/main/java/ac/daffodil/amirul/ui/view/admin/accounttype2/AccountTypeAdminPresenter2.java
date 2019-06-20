package ac.daffodil.amirul.ui.view.admin.accounttype2;

import ac.daffodil.amirul.backend.data.entity.AccountType2;
import ac.daffodil.amirul.backend.service.AccountTypeService2;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudPresenter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@ViewScope
public class AccountTypeAdminPresenter2 extends AbstractCrudPresenter<AccountType2, AccountTypeService2, AccountTypeAdminView2> {

	@Autowired
	public AccountTypeAdminPresenter2(AccountTypeAdminDataProvider2 accountTypeAdminDataProvider2, NavigationManager navigationManager,
									  AccountTypeService2 service, BeanFactory beanFactory) {
		super(navigationManager, service, AccountType2.class, accountTypeAdminDataProvider2, beanFactory);
	}
}
