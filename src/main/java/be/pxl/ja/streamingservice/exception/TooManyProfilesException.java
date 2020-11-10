package be.pxl.ja.streamingservice.exception;

public class TooManyProfilesException extends IllegalArgumentException{
    public TooManyProfilesException(String message){
        System.out.println(message);
    }
}
