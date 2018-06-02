package exception;

public class ParseFileException extends Exception {
    public ParseFileException() {
        super("При обработке фала на жестком диске, произошла ошибка.");
    }
}
