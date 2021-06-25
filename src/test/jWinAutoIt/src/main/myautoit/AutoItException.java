package test.jWinAutoIt.src.main.myautoit;

public class AutoItException extends RuntimeException {

    private static final long serialVersionUID = 2463352100008892584L;

    public AutoItException(String string) {
        super(string);
    }
    public AutoItException(String string, Throwable throwable) {
        super(string, throwable);
    }
}
