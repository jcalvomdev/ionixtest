package cl.ionix.test.restApi.search;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.ionix.test.restApi.external.model.SearchResult;
import cl.ionix.test.restApi.service.SearchService;

@SpringBootTest
class RestApiSearchServiceTests {

	@Autowired
	private SearchService searchService;
	

	@Test
	void externalSearch() {
		assertNotNull(this.searchService);
		String param = "1-9";
		SearchResult searchResult = searchService.getSearchResult(param);
		assertNotNull(searchResult);
		assertTrue(searchResult.getResponseCode() == 0);
	}
	
	@Test
	void externalSearchPerformance() {
		assertNotNull(this.searchService);
		String param = "1-9";
		SearchResult searchResult = searchService.getSearchResult(param);
		assertNotNull(searchResult);
		assertTrue(searchResult.getElapsedTime().compareTo(1000L)<0);
	}
	
	

}
