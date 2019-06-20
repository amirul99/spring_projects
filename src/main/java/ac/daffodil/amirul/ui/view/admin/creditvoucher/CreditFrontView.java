package ac.daffodil.amirul.ui.view.admin.creditvoucher;

import ac.daffodil.amirul.backend.data.entity.Credit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.ui.util.DollarPriceConverter;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView2;
import ac.daffodil.amirul.ui.view.admin.AbstractCrudView4;
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
public class CreditFrontView extends AbstractCrudView4<Credit> {

	private final CreditDataPresenter presenter;

	private final CreditFrontViewDesign userAdminViewDesign;

	//private final DollarPriceConverter priceToStringConverter;

	

	@Autowired
	public CreditFrontView(CreditDataPresenter presenter, DollarPriceConverter priceToStringConverter) {
		this.presenter = presenter;
		//this.priceToStringConverter = priceToStringConverter;
		userAdminViewDesign = new CreditFrontViewDesign();
	}

	@PostConstruct
	private void init() {
		presenter.init(this);
		
	}


	@Override
	public CreditFrontViewDesign getViewComponent() {
		return userAdminViewDesign;
	}

	@Override
	protected CreditDataPresenter getPresenter() {
		return presenter;
	}

	@Override
	protected Grid<Credit> getGrid() {
		return getViewComponent().list;
	}

	@Override
	protected void setGrid(Grid<Credit> grid) {
		getViewComponent().list = grid;
	}

	@Override
	protected Button getAdd() {
		return getViewComponent().add;
	}

	@Override
	protected TextField getSearch() {
		return getViewComponent().search;
	}

	/*@Override
	protected Focusable getFirstFormField() {
		return getViewComponent().search;
	}*/

}