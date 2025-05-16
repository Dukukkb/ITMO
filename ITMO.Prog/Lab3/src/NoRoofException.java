package src;

public class NoRoofException extends Exception {

    public NoRoofException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Ошибка жилья: " + super.getMessage();
    }
    
}
