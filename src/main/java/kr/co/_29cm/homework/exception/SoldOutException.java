package kr.co._29cm.homework.exception;

public class SoldOutException extends Exception {

    public SoldOutException() {
        super();
    }

    public SoldOutException(String msg){
        super(msg);
    }

}
