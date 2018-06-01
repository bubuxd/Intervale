package command_processor.command;


import controller.PersonController;
import exception.*;

import java.io.IOException;

public interface Command {

    boolean execute(PersonController controller, String... args) throws IncorrectArgument, NotFoundPersonById, IncorrectType, SortingIsNotPossible, PersonsListEmpty, WorkersListEmpty, IOException;

    String printHelp();

    String getName();
}
