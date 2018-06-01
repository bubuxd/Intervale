package command_processor.command;

import controller.PersonController;
import exception.IncorrectArgument;
import exception.NotFoundPersonById;

import java.io.IOException;

public class Save implements Command {

    @Override
    public boolean execute(PersonController controller, String... args) throws IncorrectArgument, NotFoundPersonById, IOException {
        if (args.length < 1)
            throw new IncorrectArgument();
        switch (args[0]){
            case "p" :  controller.saveWorkerListInCurrentFolder(); break;
            case "c" :
                if (args.length < 2)
                    throw new IncorrectArgument();
                controller.saveWorkerListInCustomPath(args[1]);
                break;
        }
        return true;
    }

    @Override
    public String printHelp() {
        return "Команда служит для сохранения текущего списка работников в файл" + System.lineSeparator() +
                "Первый аргумент - если p то сохранение в текущей директории" + System.lineSeparator() +
                "если c то сохранение по пути передаваемому вторым аргументом" + System.lineSeparator() +
                "Второй аргумент - путь к файлу на жестком диске(для с)" + System.lineSeparator();
    }

    @Override
    public String getName() {
        return "SAVE";
    }
}