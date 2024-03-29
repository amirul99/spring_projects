package ac.daffodil.amirul.ui.view;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import ac.daffodil.amirul.AbstractIT;
import ac.daffodil.amirul.ui.view.dashboard.DashboardViewElement;

public class LoginIT extends AbstractIT {

	@Test
	public void userIsRedirectedToRequestedView() {
		openLoginView(APP_URL + "#!dashboard").login("barista@vaadin.com", "barista");
		Assert.assertNotNull($(DashboardViewElement.class).first());
	}

	@Test
	public void logoutWorks() {
		//loginAsBarista();
		$(MenuElement.class).first().logout();
		Assert.assertEquals("Email", findElement(By.id("login-label")).getText());
	}

}
