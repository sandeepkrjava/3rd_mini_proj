package in.ashokit.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.reports.ExcelReports;
import in.ashokit.reports.PdfReports;
import in.ashokit.requestBinding.SearchRequest;
import in.ashokit.responseBinding.SearchResponse;
import in.ashokit.service.ReportService;

@RestController
public class ReportRestController {

	@Autowired
	private ReportService service;

	@GetMapping("/plan-names")
	public List<String> getPlans() {
		return service.getPlanNames();
	}

	@GetMapping("/plan-status")
	public List<String> getPlanStatuses() {
		return service.getPlanStatuses();

	}

	@PostMapping("/search")
	public List<SearchResponse> search(@RequestBody SearchRequest request) {
		return service.searchPlans(request);
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {

		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;fileName=plans.xls";
		response.setHeader(headerKey, headerValue);

		List<SearchResponse> records = service.searchPlans(null);
		ExcelReports excel = new ExcelReports();
		excel.generateExcel(records, response);

	}

	@GetMapping("/pdf")
	public void generatepdf(HttpServletResponse httpResponse) throws Exception {

		httpResponse.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;fileName=plans.pdf";
		httpResponse.setHeader(headerKey, headerValue);

		List<SearchResponse> records = service.searchPlans(null);
		PdfReports pdfGen = new PdfReports();
		pdfGen.generatePdf(records, httpResponse);
	}

}
