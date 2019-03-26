package team.dorm301.letterhome.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.dorm301.letterhome.exception.UsernameDuplicateException;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UsernameDuplicateException.class)
    public String usernameDuplicateExceptionHandler(HttpServletResponse response, UsernameDuplicateException exception) {
        response.setStatus(HttpStatus.CONFLICT.value());
        return exception.getMessage();
    }
}
