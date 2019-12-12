package util;

public class AppLogicException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	
	public AppLogicException(String message){
		this.setMessage(message);
	}

}
