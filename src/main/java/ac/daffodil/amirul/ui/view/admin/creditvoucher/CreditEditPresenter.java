package ac.daffodil.amirul.ui.view.admin.creditvoucher;

import ac.daffodil.amirul.app.HasLogger;
import ac.daffodil.amirul.backend.data.entity.Credit;
import ac.daffodil.amirul.backend.service.CreditService;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.util.DateTimeFormatter;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PreDestroy;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.vaadin.spring.events.EventBus.ViewEventBus;

@SpringComponent
@ViewScope
public class CreditEditPresenter implements Serializable, HasLogger{	

		private CreditEditView view;

		private final CreditService creditService;		
		private final NavigationManager navigationManager;

		private final ViewEventBus viewEventBus;
		private final DateTimeFormatter dateFormetter;
		

		@Autowired
		public CreditEditPresenter(ViewEventBus viewEventBus,NavigationManager navigationManager, CreditService creditService,DateTimeFormatter dateFormatter) {
			this.viewEventBus = viewEventBus;
			this.navigationManager = navigationManager;
			this.creditService = creditService;
			this.dateFormetter = dateFormatter;
			viewEventBus.subscribe(this);
		}
		
		@PreDestroy
		public void destroy() {
			viewEventBus.unsubscribe(this);
		}

		void init(CreditEditView view) {
			this.view = view;
		}

		/**
		 * Called when the user enters the view.
		 */
		public void enterView(Long id) {
			Credit order;
			if (id == null) {
				// New
				
			} else {
				
				}			

//			refreshView(order);
		}


		public void okPressed() {
			
				Credit order = saveOrder();	
		}


		private Credit saveOrder() {
			try {
				//filterEmptyProducts();
				Credit credit = new Credit();
				//Debit order = view.getDebit();
				Date date = new Date();
				//date = convertToDateViaInstant(view.date.getValue());
				credit.setDate(date);
				credit.setVoucherNo(view.voucherNo.getValue());
				credit.setAccountToCredit(view.accountToBeCredited.getValue());
				credit.setAccountToCredit(view.accountToBeCredited.getValue());
				credit.setCreditAmount(Double.parseDouble(view.txtCreditedAmount.getValue()));
				credit.setAccountToDebit(view.accountToBeDebited.getValue());
				credit.setDebitAmount(Double.parseDouble(view.txtDebitedAmount.getValue()));
				
				credit.setNarration(view.txtNarration.getValue());
								
				Notification.show("New credit transaction saved.");
				creditService.transferMoney(credit.getAccountToDebit(), credit.getAccountToCredit(), credit.getDebitAmount());
				return creditService.saveOrder(credit);
			} catch (ValidationException e) {
				// Should not get here if validation is setup properly
				Notification.show("Please check the contents of the fields: " + e.getMessage(), Type.ERROR_MESSAGE);
				getLogger().error("Validation error during credit save", e);
				return null;
			} catch (OptimisticLockingFailureException e) {
				// Somebody else probably edited the data at the same time
				Notification.show("Somebody else might have updated the data. Please refresh and try again.",
						Type.ERROR_MESSAGE);
				getLogger().debug("Optimistic locking error while saving credit", e);
				return null;
			} catch (Exception e) {
				// Something went wrong, no idea what
				Notification.show("An unexpected error occurred while saving. Please refresh and try again.",
						Type.ERROR_MESSAGE);
				getLogger().error("Unable to save credit", e);
				return null;
			}
		}
				
		
		public void editBackCancelPressed() {
			
			navigationManager.navigateTo(CreditFrontView.class);
		}
		
		public Date convertToDateViaInstant(LocalDate dateToConvert) {
		    return java.util.Date.from(dateToConvert.atStartOfDay()
		      .atZone(ZoneId.systemDefault())
		      .toInstant());
		}

}
