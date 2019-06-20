package ac.daffodil.amirul.ui.view.admin.debitvoucher;

import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.util.DollarPriceConverter;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView2;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerPresenter;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerView;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerViewDesign;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickShortcut;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import javax.annotation.PostConstruct;

@SpringView
public class DebitFrontView3update extends DebitFrontViewDesign2 implements View {
	
	private static final String PARAMETER_SEARCH = "search";

	private static final String PARAMETER_INCLUDE_PAST = "includePast";

	private final NavigationManager navigationManager;

	@Autowired
	public DebitFrontView3update(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}
	
	@PostConstruct
	public void init() {
		//list.addSelectionListener(e -> selectedOrder(e.getFirstSelectedItem().get()));
		add.addClickListener(e -> newOrder());
		//search.addClickListener(e -> search(searchField.getValue(), includePast.getValue()));

		// We don't want a global shortcut for enter, scope it to the panel
		//searchPanel.addAction(new ClickShortcut(searchButton, KeyCode.ENTER, null));
	}

	public void selectedOrder(Ledger order) {
		navigationManager.navigateTo(LedgerView.class, order.getId());
	}

	public void newOrder() {
		navigationManager.navigateTo(DebitEditView2.class);
	}

	public void search(String searchTerm, boolean includePast) {
		//filterGrid(searchTerm, includePast);
		String parameters = PARAMETER_SEARCH + "=" + searchTerm;
		if (includePast) {
			parameters += "&" + PARAMETER_INCLUDE_PAST;
		}
		navigationManager.updateViewParameter(parameters);
	}

	/**
	 * This is called whenever the user enters the view, regardless of if the
	 * view instance was created right before this or if an old instance was
	 * reused.
	 * <p>
	 * Here we update the data shown in the view so the user sees the latest
	 * changes.
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		Map<String, String> params = event.getParameterMap();
		String searchTerm = params.getOrDefault(PARAMETER_SEARCH, "");
		boolean includePast = params.containsKey(PARAMETER_INCLUDE_PAST);
		//filterGrid(searchTerm, includePast);
	}

	/*public void filterGrid(String searchTerm, boolean includePast) {
		list.filterGrid(searchTerm, includePast);
		searchField.setValue(searchTerm);
		this.includePast.setValue(includePast);
	}*/
	
	/////////////////////////======================

	/*private final DebitDataPresenter2 presenter;

	//private final DebitFrontViewDesign2 userAdminViewDesign;

	//private final DollarPriceConverter priceToStringConverter;

	private static final String LNAME_PROPERTY = "ledgerName";
	private static final String CODE_PROPERTY = "ledgerCode";
	private static final String GNAME_PROPERTY = "ledgerGroup";

	private static final String INITIAL_PROPERTY = "openingBalance";
	private static final String DEBIT_PROPERTY = "debit";
	private static final String CREDIT_PROPERTY = "credit";

	@Autowired
	public DebitFrontView3update(DebitDataPresenter2 presenter, DollarPriceConverter priceToStringConverter) {
		this.presenter = presenter;
		//this.priceToStringConverter = priceToStringConverter;
		userAdminViewDesign = new DebitFrontViewDesign2();
	}

	@PostConstruct
	private void init() {
		presenter.init(this);
		
	}

	

	@Override
	public DebitFrontViewDesign2 getViewComponent() {
		return userAdminViewDesign;
	}

	@Override
	protected DebitDataPresenter2 getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<Debit> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<Debit> grid) {
		getViewComponent().list = grid;
	}

//	@Override
//	protected Component getForm() {
//		return getViewComponent().form;
//	}
//
	@Override
	protected Button getAdd() {
		return getViewComponent().add;
	}


	@Override
	protected TextField getSearch() {
		return getViewComponent().search;
	}*/

	

}