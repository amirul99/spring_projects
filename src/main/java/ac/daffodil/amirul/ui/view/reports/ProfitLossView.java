package ac.daffodil.amirul.ui.view.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.vaadin.reports.PrintPreviewReport;

import com.vaadin.data.BeanPropertySet;
import com.vaadin.data.PropertyDefinition;
import com.vaadin.data.PropertySet;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.SerializableFunction;
import com.vaadin.server.SerializableSupplier;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Composite;
import com.vaadin.ui.VerticalLayout;

import ac.daffodil.amirul.backend.ReportRepository2;
import ac.daffodil.amirul.backend.data.entity.Ledger;
import ac.daffodil.amirul.backend.data.entity.Report2;
import ac.daffodil.amirul.backend.service.Report1Service;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.ColumnProperty;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import net.sf.jasperreports.web.util.WebHtmlResourceHandler;

@SpringView(name = "profit&loss")
public class ProfitLossView extends ProfitLossDesign implements View{
	
	private final Report1Service reportService;
	private final ReportRepository2 repository2;
	
	
	@Autowired
	public ProfitLossView(Report1Service reportService, ReportRepository2 repository2) {
		this.reportService = reportService;
		this.repository2 = repository2;
	}
	
	@Autowired
	private ApplicationContext appContext;

	@Override
	public void enter(ViewChangeEvent event) {
			
	}
	
	@PostConstruct
	public void init() {
		
		
		
		//btnReport.addClickListener(e-> report());
		
		
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/reports/user.jrxml");
		view.setApplicationContext(appContext);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("datasource", reportService.report());
	}
	public void reportInfo() {
	PrintPreviewReport<Report2> report = new PrintPreviewReport<>();
	report.getReportBuilder()
    .setMargins(20, 20, 40, 40)
    .setTitle("Income Statement")
    .addAutoText("For internal use only", AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 200)        
    .addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200, 10);
	report.setItems((List<? extends Report2>) reportService.report());
	report.setData(reportService.report());
	
	//System.out.println(report.setItems(reportService.report()));
	reportContent.addComponent(report);
	JasperReportsPdfView view = new JasperReportsPdfView();
	view.setUrl("classpath:/reports/user.jrxml");
	//view.setApplicationContext(appContext);
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("datasource", reportService.report());
	//reportContent.addComponent((Component) view);
	
}
	
	/*public void reportInfo() {
		PrintPreviewReport<Report2> report = new PrintPreviewReport<>();
		report.getReportBuilder()
        .setMargins(20, 20, 40, 40)
        .setTitle("Income Statement")
        .addAutoText("For internal use only", AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 200)        
        .addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200, 10);
		report.setItems((List<? extends Report2>) reportService.report());
		report.setData(reportService.report());
		
		//System.out.println(report.setItems(reportService.report()));
		reportContent.addComponent(report);
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/reports/user.jrxml");
		//view.setApplicationContext(appContext);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("datasource", reportService.report());
		//reportContent.addComponent((Component) view);
		
	}*/
	/*public ModelAndView report() {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/reports/user.jrxml");
		view.setApplicationContext(appContext);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("datasource", reportService.report());
		reportContent.addComponent(view);
		return new ModelAndView();
	}*/
	
	
	
	
	
	/*public class PrintPreviewReport<T> extends VerticalLayout {

		 public enum Format {
		        PDF(() -> new JRPdfExporter(), os -> new SimpleOutputStreamExporterOutput(os)),
		        XLS(() -> new JRXlsExporter(), os -> new SimpleOutputStreamExporterOutput(os)),
		       DOCX(() -> new JRDocxExporter(), os -> new SimpleOutputStreamExporterOutput(os)),
		        PPTX(() -> new JRPptxExporter(), os -> new SimpleOutputStreamExporterOutput(os)),
		        
		        XML(() -> new JRXmlExporter(), os -> new SimpleXmlExporterOutput(os));

		        private final SerializableSupplier<JRAbstractExporter> exporterSupplier;
		        private final SerializableFunction<OutputStream, ExporterOutput> exporterOutputFunction;

		        Format(SerializableSupplier<JRAbstractExporter> exporterSupplier, SerializableFunction<OutputStream, ExporterOutput> exporterOutputFunction) {
		            this.exporterSupplier = exporterSupplier;
		            this.exporterOutputFunction = exporterOutputFunction;
		        }
		    }
	

	    protected VerticalLayout mainLayout = new VerticalLayout();
	    protected DynamicReportBuilder reportBuilder;
	    protected DynamicReport report;
	    protected JasperPrint print;

	    private String imageServletPathPattern = "report-image?image={0}";

	    public PrintPreviewReport() {
	        reportBuilder = buildReportBuilder();
	    }

	    public PrintPreviewReport(Class<T> type) {
	        this();
	        PropertySet<T> propertySet = BeanPropertySet.get(type);
	        propertySet.getProperties().forEach(this::addColumn);
	    }

	    public PrintPreviewReport(Class<T> type, String... columnIds) {
	        this();
	        PropertySet<T> propertySet = BeanPropertySet.get(type);

	        for (int i = 0; i < columnIds.length; i++) {
	            String columnId = columnIds[i];
	            PropertyDefinition<T, ?> propertyDefinition = propertySet.getProperties()
	                    .filter(p -> columnId.equals(p.getName()))
	                    .findFirst().get();
	            addColumn(propertyDefinition);
	        }
	    }

	    protected VerticalLayout initContent() {
	        mainLayout.setMargin(false);
	        mainLayout.addClassName(PrintPreviewReport.class.getSimpleName() + "-mainLayout");
	        return mainLayout;
	    }

	    @Override
	    protected void onDetach(DetachEvent detachEvent) {
	        super.onDetach(detachEvent);
	        VaadinSession.getCurrent().getSession().removeAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
	    }

	    public void setItems(List<? extends T> items) {
	        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	            if (report == null) {
	                report = reportBuilder.build();
	            }

	            print = buildJasperPrint(items, report);
	            HtmlExporter exporter = new HtmlExporter();

	            SimpleHtmlExporterOutput exporterOutput = new SimpleHtmlExporterOutput(outputStream);
	            exporterOutput.setImageHandler(new WebHtmlResourceHandler(imageServletPathPattern));

	            exporter.setExporterOutput(exporterOutput);
	            exporter.setExporterInput(new SimpleExporterInput(print));
	            exporter.exportReport();
	            outputStream.flush();

	            Html htmlContent = new Html(outputStream.toString(StandardCharsets.UTF_8.name()));
	            Div htmlContainer = new Div(htmlContent);
	            htmlContainer.addClassName(PrintPreviewReport.class.getSimpleName() + "-htmlContainer");

	            mainLayout.removeAll();
	            mainLayout.add(htmlContainer);

	        } catch (JRException | IOException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public StreamResource getStreamResource(String fileName, SerializableSupplier<List<? extends T>> itemsSupplier, Format format) {
	        return getStreamResource(fileName, itemsSupplier, format.exporterSupplier, format.exporterOutputFunction);
	    }

	    private StreamResource getStreamResource(String fileName, SerializableSupplier<List<? extends T>> itemsSupplier, SerializableSupplier<JRAbstractExporter> exporterSupplier, SerializableFunction<OutputStream, ExporterOutput> exporterOutputFunction) {
	        List<? extends T> items = itemsSupplier.get();
	        setItems(items);

	        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	            JRAbstractExporter exporter = exporterSupplier.get();
	            exporter.setExporterOutput(exporterOutputFunction.apply(outputStream));
	            exporter.setExporterInput(new SimpleExporterInput(print));
	            exporter.exportReport();
	            outputStream.flush();

	            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	            return new StreamResource(fileName, () -> inputStream);

	        } catch (JRException | IOException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    public DynamicReportBuilder getReportBuilder() {
	        return reportBuilder;
	    }

	    public String getImageServletPathPattern() {
	        return imageServletPathPattern;
	    }

	    public void setImageServletPathPattern(String imageServletPathPattern) {
	        this.imageServletPathPattern = imageServletPathPattern;
	    }

	    protected AbstractColumn addColumn(PropertyDefinition<T, ?> propertyDefinition) {
	        AbstractColumn column = ColumnBuilder.getNew()
	                .setColumnProperty(new ColumnProperty(propertyDefinition.getName(), propertyDefinition.getType().getName()))
	                .build();

	        column.setTitle(propertyDefinition.getCaption());
	        reportBuilder.addColumn(column);

	        return column;
	    }

	    protected DynamicReportBuilder buildReportBuilder() {
	        return new FastReportBuilder()
	                .setUseFullPageWidth(true)
	                .setWhenNoData("(no data)", new Style());
	    }

	    protected JasperPrint buildJasperPrint(List<? extends T> items, DynamicReport report) throws JRException {
	        JasperPrint print = DynamicJasperHelper.generateJasperPrint(report, new ClassicLayoutManager(), items);
	        VaadinSession.getCurrent().getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);
	        return print;
	    }

	}*/

}
