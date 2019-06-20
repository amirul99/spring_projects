package ac.daffodil.amirul.ui.view.admin.debitvoucher;


import ac.daffodil.amirul.backend.data.entity.Credit;
import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.ui.util.DateTimeFormatter;
import ac.daffodil.amirul.ui.view.dashboard.DashboardIT;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateTimeToDateConverter;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import javax.annotation.PostConstruct;	

	//=======================>>>>>>><<<<<<<<=====================//
	
	
	

	@SpringView(name = "debit")
	public class DebitEditView2 extends DebitEditViewDesign implements View {
		
		private static final String DEBIT_PROPERTY = "date";
		private final DebitEditPresenter2 presenter;

		private final DateTimeFormatter dateConverter;

		private BeanValidationBinder<Debit> binder;
		private Binder.BindingBuilder<Debit, LocalDateTimeToDateConverter> cbinder;

		//private Mode mode;

		private boolean hasChanges;

		private final BeanFactory beanFactory;

		@Autowired
		public DebitEditView2(DebitEditPresenter2 presenter, BeanFactory beanFactory, DateTimeFormatter dateConverter) {
			this.presenter = presenter;
			this.beanFactory = beanFactory;
			this.dateConverter = dateConverter;
		}

		@PostConstruct
		public void init() {
			presenter.init(this);

			
			binder = new BeanValidationBinder<>(Debit.class);								
			binder.setRequiredConfigurator(null);																									
			binder.addValueChangeListener(e -> hasChanges = true);
			
			/*binder.forField(txtDebitedAmount1)
			  // Validator will be run with the String value of the field
			  .withValidator(text -> text.length() == 0,
			    "Must enter a number")
			  // Converter will only be run for strings with 4 characters
			  .withConverter(
			    new StringToDoubleConverter("Must enter a number"))			  		  			  
			  .bind(Debit::getDebitAmount, Debit::setDebitAmount);
			
			binder.forField(txtCreditedAmount)
			  // Validator will be run with the String value of the field
			  .withValidator(text -> text.length() == 0,
			    "Must enter a number")
			  // Converter will only be run for strings with 4 characters
			  .withConverter(
			    new StringToDoubleConverter("Must enter a number"))			  		  			  
			  .bind(Debit::getCreditAmount, Debit::setCreditAmount);*/
			
			//binder.forField(txtDebitedAmount1).bind(VN_PROPERTY);
			//binder.forField(txtDebitedAmount1).withConverter(new StringToDoubleConverter("")).bind(DEBIT_PROPERTY);
			//binder.forField(getViewComponent().txtCreditedAmount).withConverter(new StringToDoubleConverter("")).bind(CREDIT_PROPERTY);
			//binder.forField(getViewComponent().txtNarration).bind(N_PROPERTY);
			
			

			//addItems.addClickListener(e -> addEmptyOrderItem());
			//cancel.addClickListener(e -> presenter.editBackCancelPressed());
			//ok.addClickListener(e -> presenter.okPressed());
			add.addClickListener(e -> presenter.okPressed());
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

		

		

		protected Debit getDebit() {
			return binder.getBean();
		}
		
		

	
}