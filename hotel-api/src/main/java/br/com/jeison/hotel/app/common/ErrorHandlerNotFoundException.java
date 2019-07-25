package br.com.jeison.hotel.app.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ErrorHandlerNotFoundException extends RuntimeException {

    public ErrorHandlerNotFoundException() {
        super();
    }
    
    public ErrorHandlerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ErrorHandlerNotFoundException(String message) {
        super(message);
    }
    
    public ErrorHandlerNotFoundException(Throwable cause) {
        super(cause);
    }
    
}
