package tokobuku;

public class CustomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exception;
	private String message;
	private String status;
	
	public CustomException() {
		super();
		this.exception = "";
	}
	
	public CustomException(String e) {
		super();
		this.exception = e;
	}
	
	public CustomException(String status, String message) {
		this.message = message;
		this.status = status;
	}
	
	@Override
	public String getMessage() {
		return this.exception;
	}
	
	public String getStatus() {
		return this.status;
	}
	
}
