package ac.daffodil.amirul.ui.view.admin.group;

import javax.annotation.PostConstruct;

import ac.daffodil.amirul.backend.data.entity.AccountType2;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;

@SpringComponent
@PrototypeScope
public class AccountTypeComboBox extends ComboBox<AccountType2> {

	private final AccountTypeComboBoxDataProvider dataProvider;

	@Autowired
	public AccountTypeComboBox(AccountTypeComboBoxDataProvider dataProvider) {
		this.dataProvider = dataProvider;
		setEmptySelectionAllowed(false);
		setTextInputAllowed(false);
		setPlaceholder("Account Type");
		setItemCaptionGenerator(AccountType2::getName);
	}

	@PostConstruct
	private void initDataProvider() {
		setDataProvider(dataProvider);
	}

}
