package command_processor.command;

import controller.PersonController;
import exception.IncorrectArgument;
import exception.IncorrectType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Create implements Command {

    @Override
    public boolean execute(PersonController controller, String... args) throws IncorrectArgument, IncorrectType {
        if(args.length < 6)
            throw new IncorrectArgument();
            String personType = args[0];
            String firstName = args[1];
            String lastName = args[2];
            String midName = args[3];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate birthDate = LocalDate.parse(args[4], formatter);
            LocalDate startWorkDate = LocalDate.parse(args[5], formatter);
            int[] idAndListSize = controller.createPerson(firstName, lastName, midName, birthDate, startWorkDate, personType);
            System.out.println(String.format("Ид созданого работника : %s \nВсего работников в списке : %s", idAndListSize[0], idAndListSize[1]));
        return true;
    }

    @Override
    public String printHelp() {
        return "Команда служит для создания сотрудника" + System.lineSeparator()+
                "Первый аргумент - тип сотрудника" + System.lineSeparator()+
                "(m - менеджер, w - работник, o - другой персонал)" + System.lineSeparator()+
                "Второй аргумент - имя сотрудника"+ System.lineSeparator()+
                "Третий аргумент - фамилия сотрудника"+ System.lineSeparator()+
                "Четвертый аргумент - отчество сотрудника"+ System.lineSeparator()+
                "Пятый аргумент - дата рождения сотрудника"+ System.lineSeparator()+
                "Шестой аргумент - дата принятия на работу сотрудника"+ System.lineSeparator()
                ;
    }

    @Override
    public String getName() {
        return "CREATE";
    }

}