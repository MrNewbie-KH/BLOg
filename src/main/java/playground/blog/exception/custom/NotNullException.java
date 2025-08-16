package playground.blog.exception.custom;

public class NotNullException extends RuntimeException{
    public NotNullException(String message){
        super(message);
    }
}
