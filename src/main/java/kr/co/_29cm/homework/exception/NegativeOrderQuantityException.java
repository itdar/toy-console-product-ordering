package kr.co._29cm.homework.exception;

public class NegativeOrderQuantityException extends Exception{

    public NegativeOrderQuantityException() {
        super();
    }

    public NegativeOrderQuantityException(String msg){
        super(msg);
    }

}
