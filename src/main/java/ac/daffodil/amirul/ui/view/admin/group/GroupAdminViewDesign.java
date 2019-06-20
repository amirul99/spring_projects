package ac.daffodil.amirul.ui.view.admin.group;

import ac.daffodil.amirul.backend.data.entity.Groups;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class GroupAdminViewDesign extends VerticalLayout {
    protected TextField search;
    protected Button add;
    protected CssLayout listParent;
    protected Grid<Groups> list;
    protected VerticalLayout form;
    protected TextField groupName;
    protected TextField groupCode;
    protected AccountTypeComboBox groupAccount;
    protected Button update;
    protected Button cancel;
    protected Button delete;

    public GroupAdminViewDesign() {
		Design.read(this);
	}
}