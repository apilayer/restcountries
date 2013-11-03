package vaeke.restcountries.domain;

public class ResponseEntity {
	
	private final int status;
	private final String message;
	
	public ResponseEntity(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

}
