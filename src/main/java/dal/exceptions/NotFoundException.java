package dal.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String msg) {
        super(msg);
    }
}