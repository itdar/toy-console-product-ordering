package exception;

public class NegativeOrderQuantityException extends Exception{

    public NegativeOrderQuantityException() {
        super();
    }

    public NegativeOrderQuantityException(String msg){
        super(msg);
    }

}
