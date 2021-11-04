package by.epam.training.task02.entity;

import java.util.Objects;

/**
 * Defines a laptop.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class Laptop extends BatteryPoweredAppliance {

    private double displayInches;
    private double cpu;
    private int systemMemory;
    private int memoryRom;
    private String operationalSystem;

    public Laptop() {
        this.operationalSystem = "";
    }

    /**
     * Constructor with parameters.
     * Decimal params cannot be negative.
     * String params cannot be null.
     *
     * @param weight            Weight of an appliance, preferably in kilos.
     * @param width             Width of an appliance, preferably in cm.
     * @param height            Height of an appliance, preferably in cm.
     * @param depth             Depth of an appliance, preferably in cm.
     * @param batteryCapacity   Battery capacity of an appliance, preferably in hours.
     * @param displayInches     Display diagonal of a laptop in inches.
     *                          Cannot be greater than hypotenuse of width and height.
     * @param cpu               CPU power of a laptop. In GHz.
     * @param systemMemory      System memory capacity of a laptop. In Megabytes.
     * @param memoryRom         ROM capacity of a laptop. In Megabytes.
     * @param operationalSystem OS of a laptop.
     */
    public Laptop(double weight, double width, double height, double depth, double batteryCapacity, double displayInches, double cpu, int systemMemory, int memoryRom, String operationalSystem) {
        super(weight, width, height, depth, batteryCapacity);

        if (displayInches < 0) {
            throw new IllegalArgumentException("Display diagonal can not be negative");
        }
        if (cpu < 0) {
            throw new IllegalArgumentException("CPU power can not be negative");
        }
        if (memoryRom < 0) {
            throw new IllegalArgumentException("ROM memory capacity can not be negative");
        }
        if (systemMemory < 0) {
            throw new IllegalArgumentException("System memory capacity can not be negative");
        }
        if ((displayInches * 2.54) > Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2))) {
            throw new IllegalArgumentException("Display diagonal can not be greater than the hypotenuse" +
                    " of height and width of the laptop");
        }

        Objects.requireNonNull(operationalSystem, "OS can not be null");

        this.displayInches = displayInches;
        this.cpu = cpu;
        this.systemMemory = systemMemory;
        this.memoryRom = memoryRom;
        this.operationalSystem = operationalSystem;
    }

    public double getDisplayInches() {
        return displayInches;
    }

    /**
     * Mutator for display diagonal.
     *
     * @param displayInches New display diagonal of a laptop in inches.
     *                      Cannot be negative or greater than hypotenuse of width and height.
     */
    public void setDisplayInches(double displayInches) {

        if (displayInches < 0) {
            throw new IllegalArgumentException("Display diagonal can not be negative");
        }
        if ((displayInches * 2.54) > Math.sqrt(Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2))) {
            throw new IllegalArgumentException("Display diagonal can not be greater than the hypotenuse" +
                    " of height and width of the laptop");
        }

        this.displayInches = displayInches;
    }

    public double getCpu() {
        return cpu;
    }

    /**
     * Mutator for cpu power.
     *
     * @param cpu New cpu power. Cannot be negative.
     */
    public void setCpu(double cpu) {

        if (cpu < 0) {
            throw new IllegalArgumentException("CPU power can not be negative");
        }

        this.cpu = cpu;
    }

    public int getSystemMemory() {
        return systemMemory;
    }

    /**
     * Mutator for system memory capacity.
     *
     * @param systemMemory New system memory capacity. Cannot be negative.
     */
    public void setSystemMemory(int systemMemory) {

        if (systemMemory < 0) {
            throw new IllegalArgumentException("System memory capacity can not be negative");
        }

        this.systemMemory = systemMemory;
    }

    public int getMemoryRom() {
        return memoryRom;
    }

    /**
     * Mutator for ROM capacity.
     *
     * @param memoryRom New ROM capacity. Cannot be negative.
     */
    public void setMemoryRom(int memoryRom) {

        if (memoryRom < 0) {
            throw new IllegalArgumentException("ROM memory capacity can not be negative");
        }

        this.memoryRom = memoryRom;
    }

    public String getOperationalSystem() {
        return operationalSystem;
    }

    /**
     * Mutator for operational system.
     *
     * @param operationalSystem New OS. Cannot be null.
     */
    public void setOperationalSystem(String operationalSystem) {
        Objects.requireNonNull(operationalSystem, "OS can not be null");
        this.operationalSystem = operationalSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Laptop laptop = (Laptop) o;
        return Double.compare(laptop.displayInches, displayInches) == 0 && Double.compare(laptop.cpu, cpu) == 0 && systemMemory == laptop.systemMemory && memoryRom == laptop.memoryRom && operationalSystem.equals(laptop.operationalSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), displayInches, cpu, systemMemory, memoryRom, operationalSystem);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "weight=" + getWeight() +
                ", width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", batteryCapacity=" + getBatteryCapacity() +
                ", displayInches=" + displayInches +
                ", cpu=" + cpu +
                ", systemMemory=" + systemMemory +
                ", memoryRom=" + memoryRom +
                ", operationalSystem='" + operationalSystem + '\'' +
                '}';
    }
}
