package ocap.msr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class HttpBadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6208388440431967176L;

	public HttpBadRequestException(String message) {
		super(message);
	}

	
}
