package command_processor.command;

import controller.PersonController;
import exception.IncorrectArgument;
import exception.NotFoundPersonById;

public class Remove implements Command {

    @Override
    public boolean execute(PersonController controller, String... args) throws IncorrectArgument, NotFoundPersonById {
        if (args.length < 1)
            throw new IncorrectArgument();
        int personsListSize = controller.removePerson(args[0]);
        System.out.println(String.format("Всего работников : %s", personsListSize));
        return true;
    }

    @Override
    public String printHelp() {
        return "Команда служит для удаления работника по его id" + System.lineSeparator() +
                "Первый аргумент - ид работника которого нужно удалить"+ System.lineSeparator();
    }

    @Override
    public String getName() {
        return "REMOVE";
    }

}