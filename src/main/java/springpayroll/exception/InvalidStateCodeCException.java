package springpayroll.exception;

public class InvalidStateCodeCException extends Throwable {
    public InvalidStateCodeCException(String please_enter_correct_stateCode) { super(please_enter_correct_stateCode);
    }
}
