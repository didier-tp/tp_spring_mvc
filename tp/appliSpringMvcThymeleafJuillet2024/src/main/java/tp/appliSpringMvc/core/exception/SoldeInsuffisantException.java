package tp.appliSpringMvc.core.exception;

public class SoldeInsuffisantException extends RuntimeException {

	public SoldeInsuffisantException() {
	}

	public SoldeInsuffisantException(String message) {
		super(message);
	}


	public SoldeInsuffisantException(String message, Throwable cause) {
		super(message, cause);
	}

}
