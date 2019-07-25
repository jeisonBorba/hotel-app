package br.com.jeison.hotel.app.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ErrorHandlerBadRequestException extends RuntimeException {
	
    public ErrorHandlerBadRequestException() {
        super();
    }
    
    public ErrorHandlerBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ErrorHandlerBadRequestException(String message) {
        super(message);
    }
    
    public ErrorHandlerBadRequestException(Throwable cause) {
        super(cause);
    }

}
