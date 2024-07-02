package tp.appliSpringMvc.core.exception;

public class ConflictException extends RuntimeException {

	public ConflictException() {
		super("ConfictException: entity already exists with same id");
	}

	public ConflictException(String message) {
		super(message);
	}

	
	public ConflictException(String message, Throwable cause) {
		super(message, cause);
	}


}
