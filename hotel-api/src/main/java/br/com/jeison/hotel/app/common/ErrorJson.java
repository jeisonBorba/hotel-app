package br.com.jeison.hotel.app.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonInclude;

@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorJson implements Serializable {
	
    private Integer status;
    private String error;
    private String message;
    private String timeStamp;
    private List<String> errors;
    private String trace;
    
    public ErrorJson(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.error = (String) errorAttributes.get("error");
        this.message = (String) errorAttributes.get("message");
        this.timeStamp = errorAttributes.get("timestamp").toString();
        this.buildErros(errorAttributes);
        this.trace = (String) errorAttributes.get("trace");
    }

    private void buildErros(Map<String, Object> errorAttributes) {
        @SuppressWarnings("rawtypes")
        Collection errorObjects = (Collection) errorAttributes.get("errors");
        if (errorObjects != null && !errorObjects.isEmpty()) {
            errors = new ArrayList<String>(errorObjects.size());
            for (Object objectError : errorObjects) {
                errors.add(((ObjectError) objectError).getDefaultMessage());
            }
        }
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}

