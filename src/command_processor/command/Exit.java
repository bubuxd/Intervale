package command_processor.command;

import controller.PersonController;

import java.io.IOException;

public class Exit implements Command {

    @Override
    public boolean execute(PersonController controller, String... args) throws IOException {
        controller.saveWorkerListInCurrentFolder();
        System.out.println("Благодарим за использование лучшей программы по учету кадров :)");
        return false;
    }

    @Override
    public String printHelp() {
        return "Команда служит для выхода из программы. Во время выполнения этой команды, будет автоматически" + System.lineSeparator()+
                "сохранен список сотрудников."+ System.lineSeparator();
    }

    @Override
    public String getName() {
        return "EXIT";
    }
}