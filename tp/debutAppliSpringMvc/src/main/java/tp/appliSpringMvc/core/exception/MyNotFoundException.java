package tp.appliSpringMvc.core.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND) //404
//NB: if ResponseEntityExceptionHandler/@ControllerAdvice is present , no need of @ResponseStatus
public class MyNotFoundException extends RuntimeException {

	public MyNotFoundException() {
		super("MyNotFoundException: no entity exists with searched id");
	}

	public MyNotFoundException(String message) {
		super(message);
	}

	
	public MyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	

}
