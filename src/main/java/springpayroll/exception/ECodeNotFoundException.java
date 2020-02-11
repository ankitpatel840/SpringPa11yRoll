package springpayroll.exception;

public class ECodeNotFoundException extends Throwable {



    public ECodeNotFoundException(String e_code)
    {
        super(e_code);
    }
}
