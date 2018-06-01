package exception;

public class NotFoundPersonById extends Exception {
    public NotFoundPersonById(int id) {
        super("Работника с ид :"+id+" не существует");
    }
}
