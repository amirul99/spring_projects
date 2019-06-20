package ac.daffodil.amirul.ui.view.admin.debitvoucher;

import ac.daffodil.amirul.app.security.SecurityUtils;
import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.service.DebitService2;
import ac.daffodil.amirul.backend.service.LedgerService;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudPresenter;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudPresenter3;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerDataProvider;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerView;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import javax.validation.ValidationException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;

@SpringComponent
@ViewScope
public class DebitEditPresenter extends AbstractCrudPresenter3<Debit, DebitService2, DebitEditView> {
	//private AccountTypeService2 accountTypeService2;
	//private GroupService groupService;
	



	@Autowired
	public DebitEditPresenter(DebitEditDataProvider groupAdminDataProvider, NavigationManager navigationManager,
							  DebitService2 service, BeanFactory beanFactory) {
		super(navigationManager, service, Debit.class, groupAdminDataProvider, beanFactory);
	}
	
//	private Debit saveOrder() {
//		try {
//			//filterEmptyProducts();
//			//Order order = view.getOrder();
//			return service.saveOrder;
//		} catch (ValidationException e) {
//			// Should not get here if validation is setup properly
//			Notification.show("Please check the contents of the fields: " + e.getMessage(), Type.ERROR_MESSAGE);
//			getLogger().error("Validation error during order save", e);
//			return null;
//		} catch (OptimisticLockingFailureException e) {
//			// Somebody else probably edited the data at the same time
//			Notification.show("Somebody else might have updated the data. Please refresh and try again.",
//					Type.ERROR_MESSAGE);
//			getLogger().debug("Optimistic locking error while saving order", e);
//			return null;
//		} catch (Exception e) {
//			// Something went wrong, no idea what
//			Notification.show("An unexpected error occurred while saving. Please refresh and try again.",
//					Type.ERROR_MESSAGE);
//			getLogger().error("Unable to save order", e);
//			return null;
//		}
//	}

}
