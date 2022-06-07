package in.ashokit.service;

import java.util.List;

import in.ashokit.requestBinding.SearchRequest;
import in.ashokit.responseBinding.SearchResponse;

public interface ReportService {

	public List<String> getPlanNames();

	public List<String> getPlanStatuses();

	public List<SearchResponse> searchPlans(SearchRequest request);

}
