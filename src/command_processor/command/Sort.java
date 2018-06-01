package command_processor.command;

import controller.PersonController;
import exception.IncorrectArgument;
import exception.PersonsListEmpty;
import exception.SortingIsNotPossible;

public class Sort implements Command {

    @Override
    public boolean execute(PersonController controller, String... args) throws IncorrectArgument, SortingIsNotPossible, PersonsListEmpty {
        if (args.length < 1)
            throw new IncorrectArgument();
        controller.sort(args[0]);
        System.out.println(controller.showAllWorker());
        return true;
    }

    @Override
    public String printHelp() {
        return "Команда служит для сортировки сотрудников по параметрам" + System.lineSeparator()+
                "Значение первого аргумента :" + System.lineSeparator()+
                "Для сортировки по дате рождения : b" + System.lineSeparator()+
                "Для сортировки по дате начала работы : w" + System.lineSeparator()+
                "Для сортировки по имени : f" + System.lineSeparator()+
                "Для сортировки по фамилии : l" + System.lineSeparator()+
                "Для сортировки по отчеству : m"+ System.lineSeparator();
    }

    @Override
    public String getName() {
        return "SORT";
    }

}