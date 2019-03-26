package team.dorm301.letterhome.exception;

/**
 * 用户名重复异常
 */
public class UsernameDuplicateException extends RuntimeException {

    public UsernameDuplicateException(String message) {
        super(message);
    }
}
