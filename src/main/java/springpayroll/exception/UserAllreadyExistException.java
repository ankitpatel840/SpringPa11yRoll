package springpayroll.exception;

public class UserAllreadyExistException extends Throwable {
    public UserAllreadyExistException(String s)
    {
        super(s);
    }
}
