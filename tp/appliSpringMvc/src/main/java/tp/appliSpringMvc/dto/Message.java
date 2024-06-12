package tp.appliSpringMvc.dto;

public class Message {
	private String message;
	// ....

	public Message(String message) {
		super();
		this.message = message;
	}

	public Message() {
		this(null);
	}

	@Override
	public String toString() {
		return "Message [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
