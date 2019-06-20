package ac.daffodil.amirul.ui.view.reports;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.vaadin.reports.PrintPreviewReport;

import com.vaadin.data.BeanValidationBinder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;

import ac.daffodil.amirul.backend.CreditRepository;
import ac.daffodil.amirul.backend.DebitRepository2;
import ac.daffodil.amirul.backend.LedgerRepository;
import ac.daffodil.amirul.backend.Report1Repository;
import ac.daffodil.amirul.backend.data.entity.Credit;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.data.entity.Report1;
import ac.daffodil.amirul.backend.service.LedgerService;
import ac.daffodil.amirul.backend.service.Report1Service;
import ac.daffodil.amirul.ui.navigation.NavigationManager;
import ar.com.fdvs.dj.domain.AutoText;



@SpringView(name = "reports")
public class ReportView extends ReportLedger implements View {	
	/*private  LedgerRepository repository;
	private  LedgerService lServices;*/
	private final LedgerRepository repository;
	private final Report1Service reportService;
	private final Report1Repository reportRepository;
	//private final Ledger ledger;
	//private final BeanFactory beanFactory;
	//private final NavigationManager navigationManager;
	
	
	
	

	@Autowired
	public ReportView(LedgerRepository repository, Report1Service reportService, 
			Report1Repository reportRepository) {
		//this.ledger=ledger;
		this.repository = repository;
		this.reportService = reportService;		
		this.reportRepository = reportRepository;
		
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
			
	}
	
	@PostConstruct
	public void init() {
		reportRepository.deleteAllInBatch();
		System.out.println(reportRepository.count());			
		
		
		btnReport.addClickListener(e -> {
			//reportContent.addComponent(null);
			try {
				createReport();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
		cbLedger.setValue(null);
		
				
	}
	public void createReport() throws Exception {
		
		
		if(cbLedger.getValue()!=null) {
			
			reportService.getDetails(cbLedger.getValue());
			System.out.println(cbLedger.getValue().getLedgerName());
			//cbLedger.getValue().getLedgerName();
			
			
			PrintPreviewReport<Report1> report = new PrintPreviewReport<>(Report1.class,"rDate","rDebit","rCredit");
			
			report.getReportBuilder()
	        .setMargins(20, 20, 40, 40)
	        .setTitle(cbLedger.getValue().getLedgerName())
	        .addAutoText("For internal use only", AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 200)        
	        .addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200, 10)
	        .setPrintBackgroundOnOddRows(true);
			
			//reportRepository.findByRDateOrderByRDate(reportRepository.findAll());
			report.setItems(reportRepository.findAllByOrderByRDateAsc());
			//report.setItems(reportRepository.findAll());
			reportContent.addComponent(report);
			
		}
	}
		
		
		
		
	
	
	
	
	
	
	
	
}


