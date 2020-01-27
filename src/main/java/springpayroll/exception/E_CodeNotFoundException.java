package springpayroll.exception;

public class E_CodeNotFoundException extends  RuntimeException {


    public E_CodeNotFoundException(String s,Throwable throwable)
    {
           super(s,throwable);
    }
}
