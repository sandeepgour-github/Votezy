package in.sand.exception;

public class RequestBodyMissingException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequestBodyMissingException(String message) {
		super(message);
	}
}
