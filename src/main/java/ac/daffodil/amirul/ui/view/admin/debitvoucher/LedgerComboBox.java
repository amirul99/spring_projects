package ac.daffodil.amirul.ui.view.admin.debitvoucher;

import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.data.entity.Ledger2;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import javax.annotation.PostConstruct;

@SpringComponent
@PrototypeScope
public class LedgerComboBox extends ComboBox<Ledger> {

	private final LedgerComboBoxDataProvider dataProvider;

	@Autowired
	public LedgerComboBox(LedgerComboBoxDataProvider dataProvider) {
		this.dataProvider = dataProvider;
		setEmptySelectionAllowed(false);
		setTextInputAllowed(false);
		setPlaceholder("Ledger");
		setItemCaptionGenerator(Ledger::getLedgerName);
	}

	@PostConstruct
	private void initDataProvider() {
		setDataProvider(dataProvider);
	}

}
