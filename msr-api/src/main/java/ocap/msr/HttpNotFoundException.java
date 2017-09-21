package ocap.msr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class HttpNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7401614750578806834L;

	public HttpNotFoundException(String message) {
		super(message);
	}
	
	

}
