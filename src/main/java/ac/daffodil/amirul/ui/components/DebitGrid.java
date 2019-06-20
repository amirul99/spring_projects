package ac.daffodil.amirul.ui.components;

import ac.daffodil.amirul.backend.data.entity.Debit;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import javax.annotation.PostConstruct;

@SpringComponent
@PrototypeScope
public class DebitGrid extends Grid<Debit> {

	@Autowired
	private DebitGridDataProvider dataProvider;

	public DebitGrid() {
		addStyleName("orders-grid");
		setSizeFull();
		removeHeaderRow(0);

		setColumns("date","voucherNo","accountToDebit","debitAmount","accountToCredit","creditAmount","narration");

		// Add stylen,ames to rows
		//setStyleGenerator(DebitGrid::getRowStyle);



		// Due column
		/*Column<Order, String> dueColumn = addColumn(
				order -> twoRowCell(getTimeHeader(order.getDueDate()), String.valueOf(order.getDueTime())),
				new HtmlRenderer());
		dueColumn.setSortProperty("dueDate", "dueTime");
		dueColumn.setStyleGenerator(order -> "due");

		// Summary column
		Column<Order, String> summaryColumn = addColumn(order -> {
			Customer customer = order.getCustomer();
			return twoRowCell(customer.getFullName(), getOrderSummary(order));
		}, new HtmlRenderer()).setExpandRatio(1).setSortProperty("customer.fullName").setMinimumWidthFromContent(false);
		summaryColumn.setStyleGenerator(order -> "summary");*/
	}

	public void filterGrid(String searchTerm, boolean includePast) {
		dataProvider.setFilter(searchTerm);
		dataProvider.setIncludePast(includePast);
	}

	@PostConstruct
	protected void init() {
		setDataProvider(dataProvider);

	}

	/**
	 * Makes date into a more readable form; "Today", "Mon 7", "12 Jun"
	 * 
	 * @param //dueDate
	 *            The date to make into a string
	 * @return A formatted string depending on how far in the future the date
	 *         is.
	 */
	/*private static String getTimeHeader(LocalDate dueDate) {
		LocalDate today = LocalDate.now();
		if (dueDate.isEqual(today)) {
			return "Today";
		} else {
			// Show weekday for upcoming days
			LocalDate todayNextWeek = today.plusDays(7);
			if (dueDate.isAfter(today) && dueDate.isBefore(todayNextWeek)) {
				// "Mon 7"
				return dueDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US) + " "
						+ dueDate.getDayOfMonth();
			} else {
				// In the past or more than a week in the future
				return dueDate.getDayOfMonth() + " " + dueDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.US);
			}
		}
	}*/

	/*private static String getRowStyle(Order order) {
		String style = order.getState().name().toLowerCase();

		long days = LocalDate.now().until(order.getDueDate(), ChronoUnit.DAYS);
		if (days == 0) {
			style += " today";
		} else if (days == 1) {
			style += " tomorrow";
		}

		return style;
	}

	private static String getOrderSummary(Order order) {
		Stream<String> quantityAndName = order.getItems().stream()
				.map(item -> item.getQuantity() + "x " + item.getProduct().getName());
		return quantityAndName.collect(Collectors.joining(", "));
	}

	private static String twoRowCell(String header, String content) {
		return "<div class=\"header\">" + HtmlUtils.htmlEscape(header) + "</div><div class=\"content\">"
				+ HtmlUtils.htmlEscape(content) + "</div>";
	}*/

}
