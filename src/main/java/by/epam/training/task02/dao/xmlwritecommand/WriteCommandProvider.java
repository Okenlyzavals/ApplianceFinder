package by.epam.training.task02.dao.xmlwritecommand;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that stores commands used to
 * make XML elements out of Appliance objects.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class WriteCommandProvider {

    /**
     * Current instance of {@code WriteCommandProvider}
     */
    private static final WriteCommandProvider instance = new WriteCommandProvider();

    /**
     * A map that is used to store commands. Names of commands are used as keys.
     */
    private final Map<CommandName, ApplianceWriteCommand> commands = new HashMap<>();

    /**
     * Default constructor. Puts all commands into a map.
     * This is a singleton.
     */
    private WriteCommandProvider() {
        commands.put(CommandName.OVEN, new OvenWriteCommand());
        commands.put(CommandName.REFRIGERATOR, new RefrigeratorWriteCommand());
        commands.put(CommandName.LAPTOP, new LaptopWriteCommand());
        commands.put(CommandName.TABLET_PC, new TabletWriteCommand());
        commands.put(CommandName.SPEAKERS, new SpeakersWriteCommand());
        commands.put(CommandName.VACUUM_CLEANER, new VacuumCleanerWriteCommand());
        commands.put(CommandName.WRONG_REQUEST, new WrongRequestWriteCommand());
    }

    /**
     * Returns the current instance.
     *
     * @return An instance of {@code WriteCommandProvider}.
     */
    public static WriteCommandProvider getInstance() {
        return instance;
    }

    /**
     * Gets command from commands map.
     *
     * @param name Name of command to get.
     * @return Implementation of {@code ApplianceWriteCommand} suited for specified name.
     * {@code WrongRequestWriteCommand} if there are no such commands.
     */
    public ApplianceWriteCommand getCommand(String name) {
        return commands.get(getCommandName(name));
    }

    /**
     * Gets fitting command name from string.
     * Disregards the difference between spaces and underscores.
     *
     * @param name String name of command to get.
     * @return Implementation of {@code ApplianceWriteCommand} suited for specified name.
     * {@code WrongRequestWriteCommand} if there are no such commands.
     */
    private CommandName getCommandName(String name) {

        for (CommandName commandName : CommandName.values()) {
            if (commandName.toString().replace("_", "").equals(name.toUpperCase())
                    || commandName.toString().equals(name.toUpperCase())) {
                return commandName;
            }
        }

        return CommandName.WRONG_REQUEST;
    }

    /**
     * Enumeration of names allowed for write commands.
     */
    private enum CommandName {
        OVEN,
        REFRIGERATOR,
        LAPTOP,
        TABLET_PC,
        SPEAKERS,
        VACUUM_CLEANER,
        WRONG_REQUEST
    }
}
