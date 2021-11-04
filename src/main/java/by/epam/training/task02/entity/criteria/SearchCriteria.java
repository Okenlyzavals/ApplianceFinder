package by.epam.training.task02.entity.criteria;

/**
 * Contains enumerations of parameters that are allowed within a criteria.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public final class SearchCriteria {

    /**
     * An interface that is used in all enumerations.
     */
    public interface CriteriaParameterName {
    }

    /**
     * Parameters for any types of appliances.
     *
     * @see by.epam.training.task02.entity.Appliance
     */
    public enum AnyAppliance implements CriteriaParameterName {
        WEIGHT, WIDTH, HEIGHT, DEPTH
    }

    /**
     * Parameters for wired appliances.
     *
     * @see by.epam.training.task02.entity.WiredAppliance
     */
    public enum WiredAppliance implements CriteriaParameterName {
        POWER_CONSUMPTION
    }

    /**
     * Parameters for wireless (battery powered) appliances.
     *
     * @see by.epam.training.task02.entity.BatteryPoweredAppliance
     */
    public enum BatteryPoweredAppliance implements CriteriaParameterName {
        BATTERY_CAPACITY
    }

    /**
     * Parameters for ovens.
     *
     * @see by.epam.training.task02.entity.Oven
     */
    public enum Oven implements CriteriaParameterName {
        CAPACITY
    }

    /**
     * Parameters for laptops.
     *
     * @see by.epam.training.task02.entity.Laptop
     */
    public enum Laptop implements CriteriaParameterName {
        OS, MEMORY_ROM, SYSTEM_MEMORY, CPU, DISPLAY_INCHES
    }

    /**
     * Parameters for refrigerators.
     *
     * @see by.epam.training.task02.entity.Refrigerator
     */
    public enum Refrigerator implements CriteriaParameterName {
        FREEZER_CAPACITY, OVERALL_CAPACITY
    }

    /**
     * Parameters for vacuum cleaners.
     *
     * @see by.epam.training.task02.entity.VacuumCleaner
     */
    public enum VacuumCleaner implements CriteriaParameterName {
        FILTER_TYPE, BAG_TYPE, WAND_TYPE, MOTOR_SPEED_REGULATION, CLEANING_WIDTH
    }

    /**
     * Parameters for tablets.
     *
     * @see by.epam.training.task02.entity.TabletPC
     */
    public enum TabletPC implements CriteriaParameterName {
        DISPLAY_INCHES, MEMORY_ROM, FLASH_MEMORY_CAPACITY, COLOR
    }

    /**
     * Parameters for speakers.
     *
     * @see by.epam.training.task02.entity.Speakers
     */
    public enum Speakers implements CriteriaParameterName {
        NUMBER_OF_SPEAKERS, MINIMAL_FREQUENCY, MAXIMAL_FREQUENCY, CORD_LENGTH
    }

    /**
     * This class is not supposed to be instantiated.
     */
    private SearchCriteria() {
    }

}