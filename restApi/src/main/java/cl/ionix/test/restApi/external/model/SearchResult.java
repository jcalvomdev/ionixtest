package cl.ionix.test.restApi.external.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class SearchResult {
	
	private int responseCode;
	private String description;
	private Result result;
	@JsonIgnore
	private Long elapsedTime;
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public Long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}
