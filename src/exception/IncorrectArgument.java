package exception;

public class IncorrectArgument extends Exception {
    public IncorrectArgument() {
        super("Аргументы команды не верны");
    }
}
