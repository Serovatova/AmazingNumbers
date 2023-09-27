package numbers;

public class IllegalParameterException extends Exception {
    IllegalParameterException(String errorMessage) {
        super(errorMessage);
    }
}