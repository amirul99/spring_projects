package ac.daffodil.amirul.ui.view.admin.debitvoucher;

import ac.daffodil.amirul.app.HasLogger;
import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.backend.service.DebitService2;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.util.DateTimeFormatter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import java.io.Serializable;
//import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDateTime;

import javax.annotation.PreDestroy;
import javax.validation.ValidationException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.vaadin.spring.events.EventBus.ViewEventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

@SpringComponent
@ViewScope
public class DebitEditPresenter2 implements Serializable, HasLogger{
	//private AccountTypeService2 accountTypeService2;
	//private GroupService groupService;
	//private final DebitService2 service;

		private DebitEditView2 view;

		private final DebitService2 orderService;		
		private final NavigationManager navigationManager;

		private final ViewEventBus viewEventBus;
		private final DateTimeFormatter dateFormetter;

		

		@Autowired
		public DebitEditPresenter2(ViewEventBus viewEventBus,NavigationManager navigationManager, DebitService2 orderService,DateTimeFormatter dateFormatter) {
			this.viewEventBus = viewEventBus;
			this.navigationManager = navigationManager;
			this.orderService = orderService;
			this.dateFormetter = dateFormatter;
			viewEventBus.subscribe(this);
		}
		


		@PreDestroy
		public void destroy() {
			viewEventBus.unsubscribe(this);
		}

//		@EventBusListenerMethod
//		private void onProductInfoChange(ProductInfoChangeEvent event) {
//			updateTotalSum();
//			view.onProductInfoChanged();
//		}

//		@EventBusListenerMethod
//		private void onOrderItemDelete(OrderItemDeletedEvent event) {
//			removeOrderItem(event.getOrderItem());
//			view.onProductInfoChanged();
//		}
//
//		@EventBusListenerMethod
//		private void onOrderItemUpdate(OrderUpdatedEvent event) {
//			refresh(view.getOrder().getId());
//		}

		void init(DebitEditView2 view) {
			this.view = view;
		}

		/**
		 * Called when the user enters the view.
		 */
		public void enterView(Long id) {
			Debit order;
			if (id == null) {
				// New
				
			} else {
				
				}
			

//			refreshView(order);
		}

//		private void updateTotalSum() {
//			int sum = view.getOrder().getItems().stream().filter(item -> item.getProduct() != null)
//					.collect(Collectors.summingInt(item -> item.getProduct().getPrice() * item.getQuantity()));
//			view.setSum(sum);
//		}

//		public void editBackCancelPressed() {
//			if (view.getMode() == Mode.REPORT) {
//				// Edit order
//				view.setMode(Mode.EDIT);
//			} else if (view.getMode() == Mode.CONFIRMATION) {
//				// Back to edit
//				view.setMode(Mode.EDIT);
//			} else if (view.getMode() == Mode.EDIT) {
//				// Cancel edit
//				Long id = view.getOrder().getId();
//				if (id == null) {
//					navigationManager.navigateTo(StorefrontView.class);
//				} else {
//					enterView(id);
//				}
//			}
//		}

		public void okPressed() {
			
				//Order order = view.getOrder();
				
				//orderService.changeState(order, nextState.get(), SecurityUtils.getCurrentUser(userService));
				//refresh(order.getId());
			
				Debit order = saveOrder();
				
			
				
				// New order should still show a confirmation page
				
			
		}

//		private void refresh(Long id) {
//			Order order = orderService.findOrder(id);
//			if (order == null) {
//				view.showNotFound();
//				return;
//			}
//			refreshView(order);
//
//		}
//
//		private void refreshView(Order order) {
//			view.setOrder(order);
//			updateTotalSum();
//			if (order.getId() == null) {
//				view.setMode(Mode.EDIT);
//			} else {
//				view.setMode(Mode.REPORT);
//			}
//		}

//		private void filterEmptyProducts() {
//			LinkedList<OrderItem> emptyRows = new LinkedList<>();
//			view.getOrder().getItems().forEach(orderItem -> {
//				if (orderItem.getProduct() == null) {
//					emptyRows.add(orderItem);
//				}
//			});
//			emptyRows.forEach(this::removeOrderItem);
//		}

		private Debit saveOrder() {
			try {
				//filterEmptyProducts();
				Debit order = new Debit();
				//Debit order = view.getDebit();
				//Date date = new Date();
				Date date = Date.from(view.date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				//date = convertToDateViaInstant(view.date);
				order.setDate(date);
				order.setVoucherNo(view.voucherNo.getValue());
				order.setAccountToDebit(view.accountToBeDebited.getValue());
				order.setDebitAmount(Double.parseDouble(view.txtDebitedAmount1.getValue()));
				order.setAccountToCredit(view.accountToBeCredited.getValue());
				order.setCreditAmount(Double.parseDouble(view.txtCreditedAmount.getValue()));
				order.setNarration(view.txtNarration.getValue());
				
				/*System.out.println(view.txtCreditedAmount.getValue());
				//System.out.println(view.date.getHour());
				System.out.println(order.getVoucherNo());*/
				Notification.show("New debit transaction saved.");
				orderService.transferMoney(order.getAccountToDebit(), order.getAccountToCredit(), order.getDebitAmount());
				return orderService.saveOrder(order);
			} catch (ValidationException e) {
				// Should not get here if validation is setup properly
				Notification.show("Please check the contents of the fields: " + e.getMessage(), Type.ERROR_MESSAGE);
				getLogger().error("Validation error during debit save", e);
				return null;
			} catch (OptimisticLockingFailureException e) {
				// Somebody else probably edited the data at the same time
				Notification.show("Somebody else might have updated the data. Please refresh and try again.",
						Type.ERROR_MESSAGE);
				getLogger().debug("Optimistic locking error while saving debit", e);
				return null;
			} catch (Exception e) {
				// Something went wrong, no idea what
				Notification.show("An unexpected error occurred while saving. Please refresh and try again.",
						Type.ERROR_MESSAGE);
				getLogger().error("Unable to save debit", e);
				return null;
			}
		}
		
		
		
		public void editBackCancelPressed() {
			
			navigationManager.navigateTo(DebitFrontView3update.class);
		}
		
		/*public Date convertToDateViaInstant(LocalDate dateToConvert) {
		    return java.util.Date.from(dateToConvert.atStartOfDay()
		      .atZone(ZoneId.systemDefault())
		      .toInstant());
		}*/

//		public Optional<OrderState> getNextHappyPathState(OrderState current) {
//			final int currentIndex = happyPath.indexOf(current);
//			if (currentIndex == -1 || currentIndex == happyPath.size() - 1) {
//				return Optional.empty();
//			}
//			return Optional.of(happyPath.get(currentIndex + 1));
//		}
//
//		private void removeOrderItem(OrderItem orderItem) {
//			view.removeOrderItem(orderItem);
//			updateTotalSum();
//		}
	

	
	
//	private Debit saveOrder() {
//		try {
//			//filterEmptyProducts();
//			//Order order = view.getOrder();
//			return service.saveOrder(debit);
//		} catch (ValidationException e) {
//			// Should not get here if validation is setup properly
//			Notification.show("Please check the contents of the fields: " + e.getMessage(), Type.ERROR_MESSAGE);
//			getLogger().error("Validation error during order save", e);
//			return null;
//		} catch (OptimisticLockingFailureException e) {
//			// Somebody else probably edited the data at the same time
//			Notification.show("Somebody else might have updated the data. Please refresh and try again.",
//					Type.ERROR_MESSAGE);
//			getLogger().debug("Optimistic locking error while saving order", e);
//			return null;
//		} catch (Exception e) {
//			// Something went wrong, no idea what
//			Notification.show("An unexpected error occurred while saving. Please refresh and try again.",
//					Type.ERROR_MESSAGE);
//			getLogger().error("Unable to save order", e);
//			return null;
//		}
//	}

}
