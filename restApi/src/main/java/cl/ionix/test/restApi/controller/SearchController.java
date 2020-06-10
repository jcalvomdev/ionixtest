package cl.ionix.test.restApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.ionix.test.restApi.dto.Result;
import cl.ionix.test.restApi.dto.ResultDto;
import cl.ionix.test.restApi.external.model.SearchResult;
import cl.ionix.test.restApi.service.SearchService;

@RestController
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@PostMapping("/search")
	ResultDto search(@RequestParam("param") String param) {
		ResultDto localResponse = new ResultDto();
		SearchResult searchResult = searchService.getSearchResult(param);
		
		if(searchResult == null) {
			localResponse.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			localResponse.setDescription("Error");
			localResponse.setElapsedTime(0L);
			Result result = new Result(0);
			localResponse.setResult(result);
		}else {
			localResponse.setResponseCode(searchResult.getResponseCode());
			localResponse.setDescription(searchResult.getDescription());
			localResponse.setElapsedTime(searchResult.getElapsedTime());
			int resultCount = 0;
			resultCount = searchResult.getResult() != null ? 
					searchResult.getResult().getItems() != null ? 
							searchResult.getResult().getItems().size() : 0 
					: 0;
			Result result = new Result(resultCount);
			localResponse.setResult(result);
		}
		
		return localResponse;
	}
	

}
