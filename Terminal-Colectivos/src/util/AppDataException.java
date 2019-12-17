package util;


public class AppDataException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Throwable innerException;
	private String message;
	
	public Throwable getInnerException() {
		return innerException;
	}


	public void setInnerException(Throwable innerException) {
		this.innerException = innerException;
	}
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public AppDataException(Exception e, String message){
		this.setInnerException(e);
		this.setMessage(message);
	}


}
