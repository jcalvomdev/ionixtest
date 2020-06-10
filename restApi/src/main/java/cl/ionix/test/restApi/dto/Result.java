package cl.ionix.test.restApi.dto;

public class Result {
	
	private int registerCount;
	
	public Result(int registerCount) {
		this.registerCount = registerCount;
	}

	public int getRegisterCount() {
		return registerCount;
	}

	public void setRegisterCount(int registerCount) {
		this.registerCount = registerCount;
	}
}
