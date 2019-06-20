package ac.daffodil.amirul.ui.view.admin.ledger;

import ac.daffodil.amirul.backend.data.entity.Groups;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import javax.annotation.PostConstruct;

@SpringComponent
@PrototypeScope
public class GroupComboBox2 extends ComboBox<Groups> {

	private final GroupComboBoxDataProvider2 dataProvider;

	@Autowired
	public GroupComboBox2(GroupComboBoxDataProvider2 dataProvider) {
		this.dataProvider = dataProvider;
		setEmptySelectionAllowed(false);
		setTextInputAllowed(false);
		setPlaceholder("Group");
		setItemCaptionGenerator(Groups::getGroupName);
	}

	@PostConstruct
	private void initDataProvider() {
		setDataProvider(dataProvider);
	}

}
