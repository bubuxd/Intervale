package exception;

public class WorkersListEmpty extends Exception {
    public WorkersListEmpty() {
        super("Список подчиненных пуст");
    }
}
