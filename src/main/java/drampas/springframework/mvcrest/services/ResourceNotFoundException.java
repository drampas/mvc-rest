package drampas.springframework.mvcrest.services;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){}
    public ResourceNotFoundException(String message){
        super(message);
    }
}
