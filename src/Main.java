import command_processor.CommandProcessor;
import controller.PersonController;
import person.PersonType;

public class Main {
    public static void main(String[] args) {
        PersonController controller = new PersonController();
        CommandProcessor cp = new CommandProcessor(controller, "Cp1251");
        cp.execute();
    }
}
