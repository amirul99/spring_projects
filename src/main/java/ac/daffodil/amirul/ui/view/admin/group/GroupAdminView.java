package ac.daffodil.amirul.ui.view.admin.group;

import javax.annotation.PostConstruct;

import ac.daffodil.amirul.backend.data.entity.Groups;
import ch.qos.logback.core.joran.util.StringToObjectConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.spring.annotation.SpringView;

import ac.daffodil.amirul.ui.util.DollarPriceConverter;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

@SpringView
public class GroupAdminView extends AbstractCrudView<Groups> {

	private final GroupAdminPresenter presenter;

	private final GroupAdminViewDesign userAdminViewDesign;

	//private final DollarPriceConverter priceToStringConverter;

	private static final String GNAME_PROPERTY = "groupName";
	private static final String CODE_PROPERTY = "groupCode";
	private static final String ATNAME_PROPERTY = "groupAccount";

	@Autowired
	public GroupAdminView(GroupAdminPresenter presenter, DollarPriceConverter priceToStringConverter) {
		this.presenter = presenter;
		//this.priceToStringConverter = priceToStringConverter;
		userAdminViewDesign = new GroupAdminViewDesign();
	}

	@PostConstruct
	private void init() {
		presenter.init(this);
		// Show two columns: "name" and "price".
		getGrid().setColumns("groupName","groupCode", "groupAccount");


		// The price column is configured automatically based on the bean. As
		// we want a custom converter, we remove the column and configure it
		// manually.
		//getGrid().removeColumn(PRICE_PROPERTY);
		/*getGrid().addColumn(product -> priceToStringConverter.convertToPresentation(product.getPrice(),
				new ValueContext(getGrid()))).setSortProperty(PRICE_PROPERTY);*/
	}

	@Override
	public void bindFormFields(BeanValidationBinder<Groups> binder) {
		//binder.forField(getViewComponent().accountTypeName.toString()).bind(NAME_PROPERTY);
		//binder = new BeanValidationBinder<>(Groups.class);
		binder.forField(getViewComponent().groupName).withValidator(groupName -> groupName.length() >= 1,
				"Groups name must contain at least three characters").bind(GNAME_PROPERTY);
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
	public GroupAdminViewDesign getViewComponent() {
		return userAdminViewDesign;
	}

	@Override
	protected GroupAdminPresenter getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<Groups> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<Groups> grid) {
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
		return getViewComponent().groupName;
	}

}