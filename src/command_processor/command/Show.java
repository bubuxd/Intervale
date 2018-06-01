package command_processor.command;

import controller.PersonController;
import exception.IncorrectArgument;
import exception.NotFoundPersonById;
import exception.PersonsListEmpty;
import exception.WorkersListEmpty;

public class Show implements Command {

    @Override
    public boolean execute(PersonController controller, String... args) throws PersonsListEmpty, IncorrectArgument, NotFoundPersonById, WorkersListEmpty {
        if (args == null || args.length < 1)
            throw new IncorrectArgument();

        switch (args[0]) {
            case "a":
                System.out.println(controller.showAllWorker());
                break;
            case "p":
                if (args.length < 2)
                    throw new IncorrectArgument();
                System.out.println(controller.showWorkerInformation(args[1]));
                break;
            case "w":
                System.out.println(controller.showAllWorkersListInManager(args[1]));
                break;
                default:
                    throw new IncorrectArgument();
        }
        return true;
    }

    @Override
    public String printHelp() {
        return "Команда служит для отображения сотрудников." + System.lineSeparator() +
                "Первый аргумент " + System.lineSeparator() +
                "a для показа всех сотрудников, " + System.lineSeparator() + "" +
                "p для отображения информации по конкретному сотруднику" + System.lineSeparator() +
                "w для отображения списка сотрудников у менеджера" + System.lineSeparator() +
                "Если выбран режим отображения p вторым аргументом следует указать ид сотрудника" + System.lineSeparator();
    }

    @Override
    public String getName() {
        return "SHOW";
    }

}