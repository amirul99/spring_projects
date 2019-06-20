package ac.daffodil.amirul.ui.view.admin.accounttype2;

import ac.daffodil.amirul.backend.data.entity.AccountType2;
import ac.daffodil.amirul.ui.util.DollarPriceConverter;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView
public class AccountTypeAdminView2 extends AbstractCrudView<AccountType2> {

	private final AccountTypeAdminPresenter2 presenter;

	private final AccountTypeAdminViewDesign2 userAdminViewDesign;

	private final DollarPriceConverter priceToStringConverter;

	private static final String ANAME_PROPERTY = "name";
	private static final String OPTION = "option";

	private boolean nameRequired;

	/**
	 * Custom validator to be able to decide dynamically whether the password
	 * field is required or not (empty value when updating the user is
	 * interpreted as 'do not change the password').
	 */
	private Validator<String> passwordValidator = new Validator<String>() {

		BeanValidator passwordBeanValidator = new BeanValidator(AccountType2.class, "name");

		@Override
		public ValidationResult apply(String value, ValueContext context) {
			if (!nameRequired && value.isEmpty()) {
				// No password required and field is empty
				// OK regardless of other restrictions as the empty value will
				// not be used
				return ValidationResult.ok();
			} else {
				return passwordBeanValidator.apply(value, context);
			}
		}
	};

	@Autowired
	public AccountTypeAdminView2(AccountTypeAdminPresenter2 presenter, DollarPriceConverter priceToStringConverter) {
		this.presenter = presenter;
		this.priceToStringConverter = priceToStringConverter;
		userAdminViewDesign = new AccountTypeAdminViewDesign2();
	}

	@PostConstruct
	private void init() {
		presenter.init(this);
		// Show two columns: "name" and "price".
		getGrid().setColumns("name", "option");
		// The price column is configured automatically based on the bean. As
		// we want a custom converter, we remove the column and configure it
		// manually.
		//getGrid().removeColumn(PRICE_PROPERTY);
		//getGrid().addColumn(product -> priceToStringConverter.convertToPresentation(product.getPrice(),
				//new ValueContext(getGrid()))).setSortProperty(PRICE_PROPERTY);
	}

	@Override
	public void bindFormFields(BeanValidationBinder<AccountType2> binder) {
		binder.forField(getViewComponent().name).withValidator(name -> name.length() > 0,
				"Full name must contain at least three characters").bind(ANAME_PROPERTY);
		//binder.forField(getViewComponent().price).withConverter(priceToStringConverter).bind(PRICE_PROPERTY);
		binder.forField(getViewComponent().option).bind(OPTION);

		/*binder.forField(getViewComponent().name).withValidator(passwordValidator).bind(bean -> "",
				(bean, value) -> {
					if (value.isEmpty()) {
						// If nothing is entered in the password field, do
						// nothing
					} else {
						bean.setName(value);
					}
				});*/
		binder.bindInstanceFields(getViewComponent());
	}

	@Override
	public AccountTypeAdminViewDesign2 getViewComponent() {
		return userAdminViewDesign;
	}

	@Override
	protected AccountTypeAdminPresenter2 getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<AccountType2> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<AccountType2> grid) {
		getViewComponent().list = grid;
	}

	@Override
	protected Component getForm() {
		return getViewComponent().form;
	}

	@Override
	protected Button getAdd() {
		return getViewComponent().add;
	}

	@Override
	protected Button getCancel() {
		return getViewComponent().cancel;
	}

	@Override
	protected Button getDelete() {
		return getViewComponent().delete;
	}

	@Override
	protected Button getUpdate() {
		return getViewComponent().update;
	}

	@Override
	protected TextField getSearch() {
		return getViewComponent().search;
	}

	@Override
	protected Focusable getFirstFormField() {
		return getViewComponent().name;
	}

}