package ac.daffodil.amirul.ui;

import com.vaadin.annotations.AutoGenerated;
import ac.daffodil.amirul.ui.view.MenuElement;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.HorizontalLayoutElement;
import com.vaadin.testbench.elements.VerticalLayoutElement;
import com.vaadin.testbench.elementsbase.ServerClass;

@ServerClass("ac.daffodil.amirul.ui.MainViewDesign")
@AutoGenerated
public class MainViewDesignElement extends HorizontalLayoutElement {

	public ButtonElement getMenuButton() {
		return $(com.vaadin.testbench.elements.ButtonElement.class).id("menuButton");
	}

	public MenuElement getMenu() {
		return $(MenuElement.class).id("menu");
	}

	public ButtonElement getStorefront() {
		return $(com.vaadin.testbench.elements.ButtonElement.class).id("storefront");
	}

	public ButtonElement getDashboard() {
		return $(com.vaadin.testbench.elements.ButtonElement.class).id("dashboard");
	}

	public ButtonElement getUsers() {
		return $(com.vaadin.testbench.elements.ButtonElement.class).id("users");
	}

	public ButtonElement getProducts() {
		return $(com.vaadin.testbench.elements.ButtonElement.class).id("products");
	}

	public ButtonElement getLogout() {
		return $(com.vaadin.testbench.elements.ButtonElement.class).id("logout");
	}

	public VerticalLayoutElement getContent() {
		return $(com.vaadin.testbench.elements.VerticalLayoutElement.class).id("content");
	}
}