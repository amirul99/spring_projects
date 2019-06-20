package ac.daffodil.amirul.ui.view.dashboard;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import ac.daffodil.amirul.ui.navigation.NavigationManager;


/**
 * The dashboard view showing statistics about sales and deliveries.
 * <p>
 * Created as a single View class because the logic is so simple that using a
 * pattern like MVP would add much overhead for little gain. If more complexity
 * is added to the class, you should consider splitting out a presenter.
 */
@SpringView
public class DashboardView extends DashboardViewDesign implements View {

	private final NavigationManager navigationManager;


	@Autowired
	public DashboardView(NavigationManager navigationManager) {
		this.navigationManager = navigationManager;
	}

	@PostConstruct
	public void init() {
		setResponsive(true);
			
	}

	@Override
	public void enter(ViewChangeEvent event) {
		/*DashboardData data = fetchData();
		updateLabels(data.getDeliveryStats());
		updateGraphs(data);*/
	}
	
	/**
	 * Extends {@link PlotOptionsLine} to support zIndex. Omits getter/setter,
	 * since they are not needed in our case.
	 *
	 */
	private static class PlotOptionsLineWithZIndex extends PlotOptionsLine {
		@SuppressWarnings("unused")
		private Number zIndex;

		public PlotOptionsLineWithZIndex(Number zIndex) {
			this.zIndex = zIndex;
		};
	}

}
