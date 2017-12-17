package xyz.frame.json;
public class FrameworkJsonException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrameworkJsonException() {
		super();
	}

	public FrameworkJsonException(String message, Throwable cause) {
		super(message, cause);
	}

	public FrameworkJsonException(String message) {
		super(message);
	}

	public FrameworkJsonException(Throwable cause) {
		super(cause);
	}
}
