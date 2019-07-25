package br.com.jeison.hotel.app.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

@RestController
public class ErrorRestController implements ErrorController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private static final String PATH = "/error";

    private ErrorAttributes errorAttributes = new DefaultErrorAttributes(false);

    @RequestMapping(value = PATH)
    public ResponseEntity<ErrorJson> error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> errorAttributes = getErrorAttributes(request, false);
        Integer status = (Integer) errorAttributes.get("status");

        String strErrorProperties = errorAttributes.toString();

        LOGGER.error("Erro ao acessar o recurso");
        LOGGER.error(String.format("ErrorProperties: %s", strErrorProperties));

        return ResponseEntity.status(status).body(new ErrorJson(status, getErrorAttributes(request, false)));
    }


    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        ServletWebRequest requestAttributes = new ServletWebRequest(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}
