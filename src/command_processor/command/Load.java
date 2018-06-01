package command_processor.command;

import controller.PersonController;
import exception.IncorrectArgument;

import java.io.IOException;

public class Load implements Command {

    @Override
    public boolean execute(PersonController controller, String... args) throws IncorrectArgument, IOException {
        if(args.length < 1)
            throw new IncorrectArgument();

        int workerCount = 0;
        switch (args[0]){
            case "p" : workerCount= controller.loadCurrentFolder(); break;
            case "c" :
                if (args.length < 2)
                    throw new IncorrectArgument();
                workerCount =controller.loadCustomPath(args[1]);
                break;
        }



        System.out.println(String.format("Всего работников : %s", workerCount));
        return true;
    }

    @Override
    public String printHelp() {
        return "Команда служит для загрузки текущего списка работников из файла" + System.lineSeparator() +
                "Первый аргумент - если p то загрузка из текущей директории" + System.lineSeparator() +
                "если c то загрузка по пути передаваемому вторым аргументом" + System.lineSeparator() +
                "Второй аргумент - путь к файлу на жестком диске(для с)" + System.lineSeparator();
    }

    @Override
    public String getName() {
        return "LOAD";
    }

}