package by.epam.training.task02.dao.xmlreadcommand;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that stores commands used to
 * retrieve instances of Appliance class
 * out of repository.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public final class XMLReadCommandProvider {

    /**
     * Current instance of {@code XMLReadCommandProvider}
     */
    private static final XMLReadCommandProvider instance = new XMLReadCommandProvider();

    /**
     * A map that is used to store commands. Names of commands are used as keys.
     */
    private final Map<CommandName, ApplianceReadCommandXML> commands = new HashMap<>();

    /**
     * Default constructor. Puts all commands into a map.
     * This is a singleton.
     */
    private XMLReadCommandProvider() {
        commands.put(CommandName.OVEN, new OvenReadCommandXML());
        commands.put(CommandName.REFRIGERATOR, new RefrigeratorReadCommandXML());
        commands.put(CommandName.LAPTOP, new LaptopReadCommandXML());
        commands.put(CommandName.TABLET_PC, new TabletReadCommandXML());
        commands.put(CommandName.VACUUM_CLEANER, new VacuumCleanerReadCommandXML());
        commands.put(CommandName.SPEAKERS, new SpeakersReadCommandXML());
        commands.put(CommandName.WIRED_APPLIANCE, new WiredApplianceReadCommandXML());
        commands.put(CommandName.BATTERY_POWERED_APPLIANCE, new BatteryPoweredApplianceReadCommandXML());
        commands.put(CommandName.ANY_APPLIANCE, new AnyApplianceReadCommandXML());
        commands.put(CommandName.WRONG_REQUEST, new WrongRequestReadCommandXML());
    }

    /**
     * Returns the current instance.
     *
     * @return An instance of {@code XMLReadCommandProvider}.
     */
    public static XMLReadCommandProvider getInstance() {
        return instance;
    }

    /**
     * Gets command from commands map.
     *
     * @param name Name of command to get.
     * @return Implementation of {@code ApplianceReadCommandXML} suited for specified name.
     * {@code WrongRequestReadCommandXML} if there are no such commands.
     */
    public ApplianceReadCommandXML getCommand(String name) {
        return commands.get(getCommandName(name));
    }

    /**
     * Gets fitting command name from string.
     * Disregards the difference between spaces and underscores.
     *
     * @param name String name of command to get.
     * @return Implementation of {@code ApplianceReadCommandXML} suited for specified name.
     * {@code WrongRequestReadCommandXML} if there are no such commands.
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
     * Enumeration of names allowed for read commands.
     */
    private enum CommandName {
        ANY_APPLIANCE,
        WIRED_APPLIANCE,
        BATTERY_POWERED_APPLIANCE,
        OVEN,
        REFRIGERATOR,
        LAPTOP,
        TABLET_PC,
        SPEAKERS,
        VACUUM_CLEANER,
        WRONG_REQUEST
    }
}
