package command_processor.command;

import controller.PersonController;
import exception.IncorrectArgument;
import exception.IncorrectType;
import exception.NotFoundPersonById;
import person.Person;

public class Change implements Command {

    @Override
    public boolean execute(PersonController controller, String... args) throws IncorrectArgument, NotFoundPersonById, IncorrectType {
        if(args.length < 2)
            throw new IncorrectArgument();

        Person p =controller.changeType(args[0], args[1]);
        System.out.println(String.format("%s %s %s", p.getLastName(), p.getFirstName(), p.getPersonTypeString()));
        return true;
    }

    @Override
    public String printHelp() {
        return "Команда служит для изменения типа сотрудника" + System.lineSeparator()+
                "Первый аргумент - ид работника у которого нужно изменить тип" + System.lineSeparator()+
                "Второй аргумент - буква обозначающая желаемый тип"+ System.lineSeparator()+
                "(m - менеджер, w - работник, o - другой персонал)"+ System.lineSeparator();
    }

    @Override
    public String getName() {
        return "CHANGE";
    }

}