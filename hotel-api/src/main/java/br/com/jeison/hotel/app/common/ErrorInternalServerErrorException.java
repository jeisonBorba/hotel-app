package br.com.jeison.hotel.app.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternalServerErrorException extends RuntimeException {

    public ErrorInternalServerErrorException() {
        super();
    }
    
    public ErrorInternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ErrorInternalServerErrorException(String message) {
        super(message);
    }
    
    public ErrorInternalServerErrorException(Throwable cause) {
        super(cause);
    }
    
}
