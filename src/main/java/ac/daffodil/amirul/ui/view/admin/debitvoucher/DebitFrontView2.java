package ac.daffodil.amirul.ui.view.admin.debitvoucher;

import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.ui.util.DollarPriceConverter;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView2;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerPresenter;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerViewDesign;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView
public class DebitFrontView2 extends AbstractCrudView2<Debit> {

	private final DebitDataPresenter2 presenter;

	private final DebitFrontViewDesign2 userAdminViewDesign;

	//private final DollarPriceConverter priceToStringConverter;

	private static final String LNAME_PROPERTY = "ledgerName";
	private static final String CODE_PROPERTY = "ledgerCode";
	private static final String GNAME_PROPERTY = "ledgerGroup";

	private static final String INITIAL_PROPERTY = "openingBalance";
	private static final String DEBIT_PROPERTY = "debit";
	private static final String CREDIT_PROPERTY = "credit";

	@Autowired
	public DebitFrontView2(DebitDataPresenter2 presenter, DollarPriceConverter priceToStringConverter) {
		this.presenter = presenter;
		//this.priceToStringConverter = priceToStringConverter;
		userAdminViewDesign = new DebitFrontViewDesign2();
	}

	@PostConstruct
	private void init() {
		presenter.init(this);
		// Show two columns: "name" and "price".
		//getGrid().setColumns("ledgerName","ledgerCode", "ledgerGroup","openingBalance","debit","credit");


		// The price column is configured automatically based on the bean. As
		// we want a custom converter, we remove the column and configure it
		// manually.
		//getGrid().removeColumn(PRICE_PROPERTY);
		/*getGrid().addColumn(product -> priceToStringConverter.convertToPresentation(product.getPrice(),
				new ValueContext(getGrid()))).setSortProperty(PRICE_PROPERTY);*/
	}

	/*@Override
	public void bindFormFields(BeanValidationBinder<Debit> binder) {
		//binder.forField(getViewComponent().accountTypeName.toString()).bind(NAME_PROPERTY);
		//binder = new BeanValidationBinder<>(Groups.class);
		*//*binder.forField(getViewComponent().ledgerName).withValidator(ledgerName -> ledgerName.length() >= 1,
				"Groups name must contain at least three characters").bind(LNAME_PROPERTY);
		binder.forField(getViewComponent().ledgerCode).bind(CODE_PROPERTY);
		binder.forField(getViewComponent().ledgerGroup).bind(GNAME_PROPERTY);
		binder.forField(getViewComponent().openingBalance)
				.withConverter(new StringToDoubleConverter("Input value should be a double")).bind(INITIAL_PROPERTY);
		binder.forField(getViewComponent().debitBalance)
				.withConverter(new StringToDoubleConverter("Input value should be a double")).bind(DEBIT_PROPERTY);
		binder.forField(getViewComponent().creditBalance)
				.withConverter(new StringToDoubleConverter("Input value should be a double")).bind(CREDIT_PROPERTY);*//*
		*//*binder.forField(getViewComponent().groupAccount)
				.withConverter(new StringToIntegerConverter("Input value should be a double")).bind(ATNAME_PROPERTY);*//*
		//binder.forField(getViewComponent().groupAccount).withConverter(new StringToObjectConverter("Must be String")).bind(ATNAME_PROPERTY);
		*//*binder.forField(getViewComponent().groupCode).withValidator(groupCode -> groupCode.length() >= 1,
				"Groups Code must contain at least three characters").bind(CODE_PROPERTY);
		binder.forField(getViewComponent().groupAccount).withValidator(groupAccount -> groupAccount.getName() == null,
				"Account name must contain at least three characters").bind(ATNAME_PROPERTY);*//*

		binder.bindInstanceFields(getViewComponent());
	}*/

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
//
//	@Override
//	protected Button getCancel() {
//		return getViewComponent().cancel;
//	}
//
//	@Override
//	protected Button getDelete() {
//		return getViewComponent().delete;
//	}
//
//	@Override
//	protected Button getUpdate() {
//		return getViewComponent().update;
//	}

	@Override
	protected TextField getSearch() {
		return getViewComponent().search;
	}

	/*@Override
	protected Focusable getFirstFormField() {
		return getViewComponent().search;
	}*/

}