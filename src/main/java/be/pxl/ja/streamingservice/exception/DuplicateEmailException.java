package be.pxl.ja.streamingservice.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message){
        System.out.println(message);
    }
}
