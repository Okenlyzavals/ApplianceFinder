package by.epam.training.task02.entity;

import java.util.Objects;

/**
 * Defines a vacuum cleaner.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class VacuumCleaner extends WiredAppliance {

    private double cleaningWidth;
    private int motorSpeedRegulation;
    private String filterType;
    private String bagType;
    private String wandType;

    public VacuumCleaner() {
        super();
        bagType = "";
        wandType = "";
        filterType = "";
    }

    /**
     * Constructor with parameters.
     * Decimal params cannot be negative.
     * String params cannot be null.
     *
     * @param weight               Weight of an appliance, preferably in kilos.
     * @param width                Width of an appliance, preferably in cm.
     * @param height               Height of an appliance, preferably in cm.
     * @param depth                Depth of an appliance, preferably in cm.
     * @param powerConsumption     Power consumption of an appliance, preferably in watt.
     * @param cleaningWidth        Cleaning width of vacuum cleaner, in cm.
     * @param motorSpeedRegulation Maximal motor speed of vacuum cleaner, in RPM.
     * @param filterType           Filter type of vacuum cleaner.
     * @param bagType              Bag type of vacuum cleaner.
     * @param wandType             Wand type of vacuum cleaner.
     */
    public VacuumCleaner(double weight, double width, double height, double depth, int powerConsumption, double cleaningWidth, int motorSpeedRegulation, String filterType, String bagType, String wandType) {
        super(weight, width, height, depth, powerConsumption);

        if (cleaningWidth < 0) {
            throw new IllegalArgumentException("Cleaning width can not be negative");
        }
        if (motorSpeedRegulation < 0) {
            throw new IllegalArgumentException("Motor speed regulation can not be negative");
        }

        Objects.requireNonNull(filterType, "Filter type can not be null.");
        Objects.requireNonNull(bagType, "Bag type can not be null.");
        Objects.requireNonNull(wandType, "Wand type can not be null.");

        this.cleaningWidth = cleaningWidth;
        this.motorSpeedRegulation = motorSpeedRegulation;
        this.filterType = filterType;
        this.bagType = bagType;
        this.wandType = wandType;
    }

    public double getCleaningWidth() {
        return cleaningWidth;
    }

    /**
     * Mutator for cleaning width.
     *
     * @param cleaningWidth New cleaning width. Cannot be negative.
     */
    public void setCleaningWidth(double cleaningWidth) {

        if (cleaningWidth < 0) {
            throw new IllegalArgumentException("Cleaning width can not be negative");
        }

        this.cleaningWidth = cleaningWidth;
    }

    public int getMotorSpeedRegulation() {
        return motorSpeedRegulation;
    }

    /**
     * Mutator for motor speed regulation.
     *
     * @param motorSpeedRegulation New motor speed regulation. Cannot be negative.
     */
    public void setMotorSpeedRegulation(int motorSpeedRegulation) {

        if (motorSpeedRegulation < 0) {
            throw new IllegalArgumentException("Motor speed regulation can not be negative");
        }

        this.motorSpeedRegulation = motorSpeedRegulation;
    }

    public String getFilterType() {
        return filterType;
    }

    /**
     * Mutator for filter type.
     *
     * @param filterType New filter type. Cannot be null.
     */
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getBagType() {
        return bagType;
    }

    /**
     * Mutator for bag type.
     *
     * @param bagType New bag type. Cannot be null.
     */
    public void setBagType(String bagType) {
        Objects.requireNonNull(bagType, "Bag type can not be null.");
        this.bagType = bagType;
    }

    public String getWandType() {
        return wandType;
    }

    /**
     * Mutator for wand type.
     *
     * @param wandType New wand type. Cannot be null.
     */
    public void setWandType(String wandType) {
        Objects.requireNonNull(wandType, "Wand type can not be null.");
        this.wandType = wandType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VacuumCleaner that = (VacuumCleaner) o;
        return Double.compare(that.cleaningWidth, cleaningWidth) == 0 && motorSpeedRegulation == that.motorSpeedRegulation && filterType.equals(that.filterType) && bagType.equals(that.bagType) && wandType.equals(that.wandType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cleaningWidth, motorSpeedRegulation, filterType, bagType, wandType);
    }

    @Override
    public String toString() {
        return "VacuumCleaner{" +
                "weight=" + getWeight() +
                ", width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", powerConsumption=" + getPowerConsumption() +
                ", cleaningWidth=" + cleaningWidth +
                ", motorSpeedRegulation=" + motorSpeedRegulation +
                ", filterType=" + filterType +
                ", bagType='" + bagType + '\'' +
                ", wandType='" + wandType + '\'' +
                '}';
    }
}
