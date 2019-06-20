package ac.daffodil.amirul.ui.view.admin;

import ac.daffodil.amirul.app.HasLogger;
import ac.daffodil.amirul.backend.data.Role;
import ac.daffodil.amirul.backend.data.entity.AbstractEntity;
import ac.daffodil.amirul.backend.data.entity.Debit;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ac.daffodil.amirul.ui.view.admin.debitvoucher.DebitEditView;
import ac.daffodil.amirul.ui.view.admin.debitvoucher.DebitFrontView2;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.HasValue;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Focusable;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 *
 * @param <T>
 *            the type of entity which can be edited in the view
 */
@Secured(value = { Role.USER, Role.ADMIN })
public abstract class AbstractCrudView4<T extends AbstractEntity> implements Serializable, View, HasLogger {

	public static final String CAPTION_DISCARD = "Discard";
	public static final String CAPTION_CANCEL = "Cancel";
	public static final String CAPTION_UPDATE = "Update";
	public static final String CAPTION_ADD = "Add";

	@Override
	public void enter(ViewChangeEvent event) {
		getPresenter().viewEntered(event);
	}

	@Override
	public void beforeLeave(ViewBeforeLeaveEvent event) {
		getPresenter().beforeLeavingView(event);
	}


	@PostConstruct
	private void initLogic() {
		getGrid().addSelectionListener(e -> {
			if (!e.isUserOriginated()) {
				return;
			}

			if (e.getFirstSelectedItem().isPresent()) {
				//getPresenter().editRequest(e.getFirstSelectedItem().get());
			} else {
				throw new IllegalStateException("This should never happen as deselection is not allowed");
			}
		});

		// Force user to choose save or cancel in form once enabled
		((SingleSelectionModel<T>) getGrid().getSelectionModel()).setDeselectAllowed(false);

		//getAdd().addClickListener(event -> navigationManager.navigateTo(DebitFrontView.class));
		getAdd().addClickListener(event -> getPresenter().addNewClicked());

		// Search functionality
		getSearch().addValueChangeListener(event -> getPresenter().filterGrid(event.getValue()));

	}

	public void setDataProvider(DataProvider<T, Object> dataProvider) {
		getGrid().setDataProvider(dataProvider);
	}


	public void focusField(HasValue<?> field) {
		if (field instanceof Focusable) {
			((Focusable) field).focus();
		} else {
			getLogger().warn("Unable to focus field of type " + field.getClass().getName());
		}
	}

	protected abstract AbstractCrudPresenter4<T, ?, ? extends AbstractCrudView4<T>> getPresenter();

	protected abstract Grid<T> getGrid();

	protected abstract void setGrid(Grid<T> grid);

	protected abstract Button getAdd();

	protected abstract TextField getSearch();

}
