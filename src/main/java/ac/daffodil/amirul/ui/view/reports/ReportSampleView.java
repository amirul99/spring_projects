package ac.daffodil.amirul.ui.view.reports;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;

import ac.daffodil.amirul.backend.service.Report1Service;
import ac.daffodil.amirul.ui.navigation.NavigationManager;


@SpringView(name="reports1")
public class ReportSampleView extends ReportSampleDesign implements View{
	private final NavigationManager navigation;
	private final Report1Service reportService;
	
	@Override
	public void enter(ViewChangeEvent event) {
			
	}
	@Autowired
	
	public ReportSampleView(NavigationManager navigation, Report1Service reportService) {
		
		this.navigation = navigation;
		this.reportService= reportService;
		
		
	}
	
	@Autowired
	private ApplicationContext appContext;
	
	@PostConstruct
	public void init() {
		
		btnGeneral.addClickListener(e-> navigation.navigateTo(ReportView.class));
		btnProfitLoss.addClickListener(e-> navigation.navigateTo(ProfitLossView.class));
		
		btnBalanceSheet.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	
            	report();
            
            }
        });
		
	}
	
	public ModelAndView report() {
	JasperReportsPdfView view = new JasperReportsPdfView();
	view.setUrl("classpath:/reports/user.jrxml");
	view.setApplicationContext(appContext);
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("datasource", reportService.report());
	return new ModelAndView();
}
	

}
