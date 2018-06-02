package command_processor.command;

import controller.PersonController;
import exception.IncorrectArgument;
import exception.NotFoundPersonById;

public class Remove implements Command {

    @Override
    public boolean execute(PersonController controller, String... args) throws IncorrectArgument, NotFoundPersonById {
        if (args.length < 1)
            throw new IncorrectArgument();
        if(args.length == 1) {
            int personsListSize = controller.removePerson(args[0]);
            System.out.println(String.format("Всего работников : %s", personsListSize));
        }else {
            int personsListSize = controller.removeWorkerInManager(args[0], args[1]);
            System.out.println(String.format("Всего работников у данного менеджера: %s", personsListSize));
        }
        return true;
    }

    @Override
    public String printHelp() {
        return "Команда служит для удаления работника по его id" + System.lineSeparator() +
                "Для удаления работника из общего списка нужен один аргумент" + System.lineSeparator() +
                "Первый аргумент - ид работника которого нужно удалить"+ System.lineSeparator()+
                "Для удаления работника из списка работников менеджера нужна два аргумента" + System.lineSeparator() +
                "Первый аргумент - ид менеджера" + System.lineSeparator() +
                "Второй аргумент - ид работника которого нужно удалить" + System.lineSeparator();
    }

    @Override
    public String getName() {
        return "REMOVE";
    }

}