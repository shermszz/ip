public class ShermszzException extends Exception {
    private String errorMessage;

    public ShermszzException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return this.errorMessage;
    }
}
