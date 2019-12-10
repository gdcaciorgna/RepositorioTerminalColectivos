package util;


public class AppDataException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Throwable innerException;
	private String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public AppDataException(Exception e, String message){
		this.innerException=e;
		this.setMessage(message);
	}

}
