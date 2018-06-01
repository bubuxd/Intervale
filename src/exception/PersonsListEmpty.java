package exception;

public class PersonsListEmpty extends Throwable {
    public PersonsListEmpty() {
        super("Список работников пуст");
    }
}
