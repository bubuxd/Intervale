package command_processor.command;

import controller.PersonController;
import exception.IncorrectArgument;
import exception.NotFoundPersonById;
import exception.WorkersListEmpty;
import person.Manager;
import person.Other;
import person.Person;

public class Set implements Command {

    @Override
    public boolean execute(PersonController controller, String... args) throws NotFoundPersonById, IncorrectArgument, WorkersListEmpty {
        if (!args[0].equals("m") && !args[0].equals("d"))
            throw new IncorrectArgument();
        switch (args[0]) {
            case "m":
                Manager m;
                m = controller.setWorker(args[1], args[2]);
                System.out.println(m.printWorkers(controller.getWorkersList()));
                break;
            case "d":
                Other o;
                o = controller.setDescription(args[1], args[2]);
                System.out.println(String.format("%s %s : %s",  o.getLastName(),  o.getFirstName(), o.getDescription()));
        }

        return true;
    }

    @Override
    public String printHelp() {
        return "Команда служит для добавления менеджеру работника либо установке описания" + System.lineSeparator() +
                "Первый аргумент служит для выбора режима работы команды " + System.lineSeparator() +
                "m - добавить менеджеру работника, d - добавить описание работнику" + System.lineSeparator() +
                "Для добавления работника" + System.lineSeparator() +
                "Второй аргумент - ид менеджера которому устанавливается работник" + System.lineSeparator() +
                "Третий аргумент - ид работника который добавляется менеджеру" +
                "Для установки описания" + System.lineSeparator() +
                "Второй аргумент - ид работника которому устанавливается описание" + System.lineSeparator() +
                "Третий аргумент - описание" + System.lineSeparator();
    }

    @Override
    public String getName() {
        return "SET";
    }

}