package command_processor.command;

import command_processor.CommandProcessor;
import controller.PersonController;

public class Help implements Command {
    private static final String MSG_DELIM = "==========================================";
    private static final String MSG_COMMAND_NOT_FOUND = "Команда не найдена";

    @Override
    public boolean execute(PersonController controller, String... args) {
        if (args == null) {
            System.out.println("Доступные команды:\n" + MSG_DELIM);
            for (Command cmd : CommandProcessor.getCommands().values()) {
                System.out.println(cmd.getName() + ": " + cmd.printHelp());
            }
            System.out.println(MSG_DELIM);
        } else {
            for (String cmd : args) {
                System.out.println("Аргументы команды " + cmd + ":\n" + MSG_DELIM);
                Command command = CommandProcessor.getCommands().get(cmd.toUpperCase());
                if (command == null) {
                    System.out.println(MSG_COMMAND_NOT_FOUND);
                } else {
                    command.printHelp();
                }
                System.out.println(MSG_DELIM);
            }
        }
        return true;
    }

    @Override
    public String printHelp() {
        return "Команда печатает список всех команд"+ System.lineSeparator();
    }

    @Override
    public String getName() {
        return "HELP";
    }

}