package ac.daffodil.amirul.ui;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import ac.daffodil.amirul.ui.view.admin.accounttype2.AccountTypeAdminView2;
import ac.daffodil.amirul.ui.view.admin.creditvoucher.CreditEditView;
import ac.daffodil.amirul.ui.view.admin.creditvoucher.CreditFrontView;
import ac.daffodil.amirul.ui.view.admin.debitvoucher.DebitFrontView2;
import ac.daffodil.amirul.ui.view.admin.debitvoucher.DebitFrontView3update;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerView;
//import ac.daffodil.amirul.ui.view.storefront.StorefrontView;
import com.vaadin.spring.access.SecuredViewAccessControl;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.navigator.ViewLeaveAction;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.view.admin.group.GroupAdminView;
import ac.daffodil.amirul.ui.view.admin.user.UserAdminView;
import ac.daffodil.amirul.ui.view.dashboard.DashboardView;
import ac.daffodil.amirul.ui.view.reports.ReportLedger;
import ac.daffodil.amirul.ui.view.reports.ReportSampleView;
import ac.daffodil.amirul.ui.view.reports.ReportView;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

/**
 * The main view containing the menu and the content area where actual views are
 * shown.
 * <p>
 * Created as a single View class because the logic is so simple that using a
 * pattern like MVP would add much overhead for little gain. If more complexity
 * is added to the class, you should consider splitting out a presenter.
 */
@SpringViewDisplay
@UIScope
public class MainView extends MainViewDesign implements ViewDisplay {

	private final Map<Class<? extends View>, Button> navigationButtons = new HashMap<>();
	private final NavigationManager navigationManager;
	private final SecuredViewAccessControl viewAccessControl;

	@Autowired
	public MainView(NavigationManager navigationManager, SecuredViewAccessControl viewAccessControl) {
		this.navigationManager = navigationManager;
		this.viewAccessControl = viewAccessControl;
	}

	@PostConstruct
	public void init() {
		//attachNavigation(storefront, StorefrontView.class);
		attachNavigation(dashboard, DashboardView.class);
		attachNavigation(users, UserAdminView.class);
		//attachNavigation(products, ProductAdminView.class);
		//attachNavigation(products2,CreditAdminView.class);
		attachNavigation(accountType, AccountTypeAdminView2.class);
		attachNavigation(groups, GroupAdminView.class);
		attachNavigation(ledgers, LedgerView.class);
		attachNavigation(credit, CreditFrontView.class);
		attachNavigation(debit, DebitFrontView2.class);
		attachNavigation(reports, ReportSampleView.class);
		//===main dabit
		//attachNavigation(debit, DebitFrontView3update.class);
		

		logout.addClickListener(e -> logout());
	}

	/**
	 * Makes clicking the given button navigate to the given view if the user
	 * has access to the view.
	 * <p>
	 * If the user does not have access to the view, hides the button.
	 *
	 * @param navigationButton
	 *            the button to use for navigatio
	 * @param targetView
	 *            the view to navigate to when the user clicks the button
	 */
	private void attachNavigation(Button navigationButton, Class<? extends View> targetView) {
		boolean hasAccessToView = viewAccessControl.isAccessGranted(targetView);
		navigationButton.setVisible(hasAccessToView);

		if (hasAccessToView) {
			navigationButtons.put(targetView, navigationButton);
			navigationButton.addClickListener(e -> navigationManager.navigateTo(targetView));
		}
	}

	@Override
	public void showView(View view) {
		content.removeAllComponents();
		content.addComponent(view.getViewComponent());

		navigationButtons.forEach((viewClass, button) -> button.setStyleName("selected", viewClass == view.getClass()));

		Button menuItem = navigationButtons.get(view.getClass());
		String viewName = "";
		if (menuItem != null) {
			viewName = menuItem.getCaption();
		}
		activeViewName.setValue(viewName);
	}

	/**
	 * Logs the user out after ensuring the currently open view has no unsaved
	 * changes.
	 */
	public void logout() {
		ViewLeaveAction doLogout = () -> {
			UI ui = getUI();
			ui.getSession().getSession().invalidate();
			ui.getPage().reload();
		};

		navigationManager.runAfterLeaveConfirmation(doLogout);
	}

}
