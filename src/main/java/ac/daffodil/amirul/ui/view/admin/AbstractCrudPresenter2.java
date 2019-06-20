package ac.daffodil.amirul.ui.view.admin;

import ac.daffodil.amirul.app.HasLogger;
import ac.daffodil.amirul.backend.data.entity.AbstractEntity;
import ac.daffodil.amirul.backend.service.CrudService;
import ac.daffodil.amirul.backend.service.UserFriendlyDataException;
import ac.daffodil.amirul.ui.components.ConfirmPopup;
import ac.daffodil.amirul.ui.navigation.NavigationManager;

import ac.daffodil.amirul.ui.view.admin.debitvoucher.DebitEditView;
import ac.daffodil.amirul.ui.view.admin.debitvoucher.DebitEditView2;
import ac.daffodil.amirul.ui.view.admin.ledger.LedgerView;
import com.vaadin.data.*;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.vaadin.artur.spring.dataprovider.FilterablePageableDataProvider;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractCrudPresenter2<T extends AbstractEntity, S extends CrudService<T>, V extends AbstractCrudView2<T>>
		implements HasLogger, Serializable {

	private V view;

	private final NavigationManager navigationManager;

	private final S service;

	private FilterablePageableDataProvider<T, Object> dataProvider;

	private BeanValidationBinder<T> binder;

	// The model for the view. Not extracted to a class to reduce clutter. If
	// the model becomes more complex, it could be encapsulated in a separate
	// class.
	private T editItem;

	private final BeanFactory beanFactory;

	private final Class<T> entityType;

	protected AbstractCrudPresenter2(NavigationManager navigationManager, S service, Class<T> entityType,
                                     FilterablePageableDataProvider<T, Object> dataProvider, BeanFactory beanFactory) {
		this.service = service;
		this.navigationManager = navigationManager;
		this.entityType = entityType;
		this.dataProvider = dataProvider;
		this.beanFactory = beanFactory;
		createBinder();
	}

	public void viewEntered(ViewChangeEvent event) {
		if (!event.getParameters().isEmpty()) {
			//editRequest(event.getParameters());
		}
	}

	public void beforeLeavingView(ViewBeforeLeaveEvent event) {
		runWithConfirmation(event::navigate, () -> {
			// Nothing special needs to be done if user aborts the navigation
		});
	}

	protected void createBinder() {
		binder = new BeanValidationBinder<>(getEntityType());
		//binder.addStatusChangeListener(this::onFormStatusChange);
	}

	protected BeanValidationBinder<T> getBinder() {
		return binder;
	}

	protected S getService() {
		return service;
	}

	protected void filterGrid(String filter) {
		dataProvider.setFilter(filter);
	}

	protected T loadEntity(long id) {
		return service.load(id);
	}

	protected Class<T> getEntityType() {
		return entityType;
	}

	private T createEntity() {
		try {
			return getEntityType().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new UnsupportedOperationException(
					"Entity of type " + getEntityType().getName() + " is missing a public no-args constructor", e);
		}
	}

	protected void deleteEntity(T entity) {
		if (entity.isNew()) {
			throw new IllegalArgumentException("Cannot delete an entity which is not in the database");
		} else {
			service.delete(entity.getId());
		}
	}

	public void init(V view) {
		this.view = view;
		view.setDataProvider(dataProvider);
//		view.bindFormFields(getBinder());
//		view.showInitialState();
	}

	protected V getView() {
		return view;
	}


	public void addNewClicked() {
		runWithConfirmation(() -> {
			navigationManager.navigateTo(DebitEditView2.class);
			T entity = createEntity();
			//editItem(entity);
		}, () -> {
		});
	}

	/**
	 * Runs the given command if the form contains no unsaved changes or if the
	 * user clicks ok in the confirmation dialog telling about unsaved changes.
	 *
	 * @param onConfirmation
	 *            the command to run if there are not changes or user pushes
	 *            {@literal confirm}
	 * @param onCancel
	 *            the command to run if there are changes and the user pushes
	 *            {@literal cancel}
	 * @return <code>true</code> if the {@literal confirm} command was run
	 *         immediately, <code>false</code> otherwise
	 */
	private void runWithConfirmation(Runnable onConfirmation, Runnable onCancel) {
		if (hasUnsavedChanges()) {
			ConfirmPopup confirmPopup = beanFactory.getBean(ConfirmPopup.class);
			confirmPopup.showLeaveViewConfirmDialog(view, onConfirmation, onCancel);
		} else {
			onConfirmation.run();
		}
	}

	private boolean hasUnsavedChanges() {
		return editItem != null && getBinder().hasChanges();
	}

}
