package ac.daffodil.amirul.ui.view.admin.creditvoucher;

import ac.daffodil.amirul.backend.data.entity.Credit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.ui.util.DateTimeFormatter;
import net.sf.cglib.core.Local;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateTimeToDateConverter;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;

	
	/*
	 * //=======================>>>>>>><<<<<<<<=====================//
	 */	
		

	@SpringView(name = "credit")
	public class CreditEditView extends CreditEditViewDesign implements View {

		/*public enum Mode {
			EDIT, REPORT, CONFIRMATION;
		}*/
		
		private static final String DEBIT_PROPERTY = "date";
		private final CreditEditPresenter presenter;

		private final DateTimeFormatter dateConverter;

		private BeanValidationBinder<Credit> binder;
		private Binder.BindingBuilder<Credit, LocalDateTimeToDateConverter> cbinder;

		//private Mode mode;

		private boolean hasChanges;

		private final BeanFactory beanFactory;

		@Autowired
		public CreditEditView(CreditEditPresenter presenter, BeanFactory beanFactory, DateTimeFormatter dateConverter) {
			this.presenter = presenter;
			this.beanFactory = beanFactory;
			this.dateConverter = dateConverter;
		}

		@PostConstruct
		public void init() {
			presenter.init(this);

			binder = new BeanValidationBinder<>(Credit.class);
			
			binder.setRequiredConfigurator(null);
			
			

			//binder.bindInstanceFields(this);
			
			
			//=============date format
									
			// Track changes manually as we use setBean and nested binders
			binder.addValueChangeListener(e -> hasChanges = true);
			add.addClickListener(e ->presenter.okPressed());
			cancel.addClickListener(e ->presenter.editBackCancelPressed());
		}
		

		@Override
		public void enter(ViewChangeEvent event) {
			//this.(event);
			String orderId = event.getParameters();
			if ("".equals(orderId)) {
				//presenter.enterView(null);
				presenter.enterView(null);
			} else {
				presenter.enterView(Long.valueOf(orderId));
			}
		}
		
		protected Credit getDebit() {
			
			return binder.getBean();
		}
		

		

	
}