package command_processor;

import command_processor.command.*;
import command_processor.command.Change;
import controller.PersonController;
import exception.*;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class CommandProcessor {

    private static final String MSG_COMMAND_NOT_FOUND = "Команда не найдена";
    private static Map<String, Command> commands;
    private String consoleEncoding;
    private PersonController personController;

    static {
        commands = new TreeMap<>();

        Command cmd = new Help();
        commands.put(cmd.getName(), cmd);

        cmd = new Exit();
        commands.put(cmd.getName(), cmd);

        cmd = new Change();
        commands.put(cmd.getName(), cmd);

        cmd = new Create();
        commands.put(cmd.getName(), cmd);

        cmd = new Load();
        commands.put(cmd.getName(), cmd);

        cmd = new Remove();
        commands.put(cmd.getName(), cmd);

        cmd = new Set();
        commands.put(cmd.getName(), cmd);

        cmd = new Show();
        commands.put(cmd.getName(), cmd);

        cmd = new Sort();
        commands.put(cmd.getName(), cmd);

        cmd = new Save();
        commands.put(cmd.getName(), cmd);

    }

    public CommandProcessor(PersonController controller, String consoleEncoding) {
        this.personController = controller;
        this.consoleEncoding = consoleEncoding;

    }

    public static Map<String, Command> getCommands() {
        return CommandProcessor.commands;
    }

    public void execute() {
        boolean result = true;
        Scanner scanner = new Scanner(System.in, consoleEncoding);
        do {
            System.out.print("> ");
            String fullCommand = scanner.nextLine();
            ParsedCommand pc = new ParsedCommand(fullCommand);
            if (pc.getCommand() == null || "".equals(pc.getCommand())) {
                continue;
            }
            Command cmd = commands.get(pc.getCommand().toUpperCase());
            if (cmd == null) {
                System.out.println(MSG_COMMAND_NOT_FOUND);
                continue;
            }
            try {
                result = cmd.execute(personController, pc.getArgs());
            } catch (IncorrectArgument incorrectArgument) {
                System.out.println(incorrectArgument.getLocalizedMessage());
            } catch (NotFoundPersonById notFoundPersonById) {
                System.out.println(notFoundPersonById.getLocalizedMessage());
            } catch (IncorrectType incorrectType) {
                System.out.println(incorrectType.getLocalizedMessage());
            } catch (SortingIsNotPossible sortingIsNotPossible) {
                System.out.println(sortingIsNotPossible.getLocalizedMessage());
            } catch (PersonsListEmpty personsListEmpty) {
                System.out.println(personsListEmpty.getLocalizedMessage());
            } catch (WorkersListEmpty workersListEmpty) {
                System.out.println(workersListEmpty.getLocalizedMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (result);
    }

}