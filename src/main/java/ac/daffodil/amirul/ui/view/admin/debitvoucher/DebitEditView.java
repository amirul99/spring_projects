package ac.daffodil.amirul.ui.view.admin.debitvoucher;

import ac.daffodil.amirul.backend.DebitRepository2;
import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.ui.util.DollarPriceConverter;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView2;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView3;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerPresenter;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerViewDesign;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import org.openqa.selenium.remote.html5.AddApplicationCache;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView
public class DebitEditView extends AbstractCrudView3<Debit> {

	private final DebitEditPresenter presenter;
	private final DebitRepository2 repository2;
	
	private BeanValidationBinder<Debit> binder;
	private  Debit debit;

	private final DebitEditViewDesign userAdminViewDesign;
	//private final DebitEditViewDesign userAdminViewDesign;

	//private final DollarPriceConverter priceToStringConverter;

	private static final String DATE_PROPERTY = "date";
	private static final String VN_PROPERTY = "voucherNo";
	private static final String DEBIT_PROPERTY = "debitAmount";

	private static final String CREDIT_PROPERTY = "creditAmount";
	private static final String N_PROPERTY = "narration";
	//private static final String CREDIT_PROPERTY = "credit";

	@Autowired
	public DebitEditView(DebitEditPresenter presenter, DebitRepository2 repository2) {
		this.presenter = presenter;
		this.repository2 = repository2;
		//this.priceToStringConverter = priceToStringConverter;
		userAdminViewDesign = new DebitEditViewDesign();
		//userAdminViewDesign.add.addClickListener(repository2.save(de));
	}

	@PostConstruct
	private void init() {
		presenter.init(this);
		//userAdminViewDesign.add.addClickListener(e->repository2.save(debit));
		

		//userAdminViewDesign.add.addClickListener(event -> Notification.show("Hello"));
		// Show two columns: "name" and "price".
		//getGrid().setColumns("ledgerName","ledgerCode", "ledgerGroup","openingBalance","debit","credit");


		// The price column is configured automatically based on the bean. As
		// we want a custom converter, we remove the column and configure it
		// manually.
		//getGrid().removeColumn(PRICE_PROPERTY);
		/*getGrid().addColumn(product -> priceToStringConverter.convertToPresentation(product.getPrice(),
				new ValueContext(getGrid()))).setSortProperty(PRICE_PROPERTY);*/
	}

	@Override
	public void bindFormFields(BeanValidationBinder<Debit> binder) {

		//binder.forField(getViewComponent().date).withConverter(new LocalDateToDateConverter()).bind(DATE_PROPERTY);
		binder.forField(getViewComponent().voucherNo).bind(VN_PROPERTY);
		binder.forField(getViewComponent().txtDebitedAmount1).withConverter(new StringToDoubleConverter("")).bind(DEBIT_PROPERTY);
		binder.forField(getViewComponent().txtCreditedAmount).withConverter(new StringToDoubleConverter("")).bind(CREDIT_PROPERTY);
		binder.forField(getViewComponent().txtNarration).bind(N_PROPERTY);

		//binder.forField(getViewComponent().accountTypeName.toString()).bind(NAME_PROPERTY);
		//binder = new BeanValidationBinder<>(Groups.class);
		/*binder.forField(getViewComponent().ledgerName).withValidator(ledgerName -> ledgerName.length() >= 1,
				"Groups name must contain at least three characters").bind(LNAME_PROPERTY);
		binder.forField(getViewComponent().ledgerCode).bind(CODE_PROPERTY);
		binder.forField(getViewComponent().ledgerGroup).bind(GNAME_PROPERTY);
		binder.forField(getViewComponent().openingBalance)
				.withConverter(new StringToDoubleConverter("Input value should be a double")).bind(INITIAL_PROPERTY);
		binder.forField(getViewComponent().debitBalance)
				.withConverter(new StringToDoubleConverter("Input value should be a double")).bind(DEBIT_PROPERTY);
		binder.forField(getViewComponent().creditBalance)
				.withConverter(new StringToDoubleConverter("Input value should be a double")).bind(CREDIT_PROPERTY);*/
		/*binder.forField(getViewComponent().groupAccount)
				.withConverter(new StringToIntegerConverter("Input value should be a double")).bind(ATNAME_PROPERTY);*/
		//binder.forField(getViewComponent().groupAccount).withConverter(new StringToObjectConverter("Must be String")).bind(ATNAME_PROPERTY);
		/*binder.forField(getViewComponent().groupCode).withValidator(groupCode -> groupCode.length() >= 1,
				"Groups Code must contain at least three characters").bind(CODE_PROPERTY);
		binder.forField(getViewComponent().groupAccount).withValidator(groupAccount -> groupAccount.getName() == null,
				"Account name must contain at least three characters").bind(ATNAME_PROPERTY);*/

		binder.bindInstanceFields(getViewComponent());
	}

	@Override
	public DebitEditViewDesign getViewComponent() {
		return userAdminViewDesign;
	}

	@Override
	protected DebitEditPresenter getPresenter() {
		return presenter;
	}

	/*@Override
	protected Grid<Debit> getGrid() {

		return getViewComponent().list;
	}*/

	/*@Override
	protected void setGrid(Grid<Debit> grid) {

		 getViewComponent().list = grid;
	}*/

	/*@Override
	protected Component getForm() {
		return null;
	}*/

	@Override
	protected Button getAdd() {
		//getAdd().addClickListener(e-> Notification.show("Save Button clicked"));
		return getViewComponent().add;
	}

	@Override
	protected Button getCancel() {
		return getViewComponent().cancel;
	}
	
	protected Debit getDebit() {
		return binder.getBean();
	}

	/*@Override
	protected Button getDelete() {
		return null;
	}*/

	/*@Override
	protected Button getUpdate() {
		return null;
	}*/

	/*@Override
	protected TextField getSearch() {
		return null;
	}*/

	@Override
	protected Focusable getFirstFormField() {
		return getViewComponent().voucherNo;
		//return getViewComponent().date;
	}

}