package by.epam.training.task02.entity;

import java.util.Objects;

/**
 * Defines a tablet.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class TabletPC extends BatteryPoweredAppliance {

    private double displayInches;
    private int memoryRom;
    private double flashMemoryCapacity;
    private String color;

    public TabletPC() {
        super();
        this.color = "";
    }

    /**
     * Constructor with parameters.
     * Decimal params cannot be negative.
     * String params cannot be null.
     *
     * @param weight              Weight of an appliance, preferably in kilos.
     * @param width               Width of an appliance, preferably in cm.
     * @param height              Height of an appliance, preferably in cm.
     * @param depth               Depth of an appliance, preferably in cm.
     * @param batteryCapacity     Battery capacity of an appliance, preferably in hours.
     * @param displayInches       Display diagonal of a tablet in inches.
     *                            Cannot be greater than hypotenuse of width and height.
     * @param memoryRom           ROM capacity of a tablet. In Megabytes.
     * @param flashMemoryCapacity Flash memory capacity of a tablet. In Gigabytes
     * @param color               Color of a tablet.
     */
    public TabletPC(double weight, double width, double height, double depth, double batteryCapacity, double displayInches, int memoryRom, double flashMemoryCapacity, String color) {
        super(weight, width, height, depth, batteryCapacity);

        if (displayInches < 0) {
            throw new IllegalArgumentException("Display diagonal can not be negative");
        }
        if (memoryRom < 0) {
            throw new IllegalArgumentException("ROM memory capacity can not be negative");
        }
        if (flashMemoryCapacity < 0) {
            throw new IllegalArgumentException("Flash memory capacity can not be negative");
        }
        if ((displayInches * 2.54) > Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2))) {
            throw new IllegalArgumentException("Display diagonal can not be greater than the hypotenuse" +
                    " of height and width of the laptop");
        }

        Objects.requireNonNull(color, "Color description can not be null");

        this.displayInches = displayInches;
        this.memoryRom = memoryRom;
        this.flashMemoryCapacity = flashMemoryCapacity;
        this.color = color;
    }

    public double getDisplayInches() {
        return displayInches;
    }

    /**
     * Mutator for display diagonal.
     *
     * @param displayInches New display diagonal of a tablet in inches.
     *                      Cannot be negative or greater than hypotenuse of width and height.
     */
    public void setDisplayInches(double displayInches) {

        if (displayInches < 0) {
            throw new IllegalArgumentException("Display diagonal can not be negative");
        }
        if ((displayInches * 2.54) > Math.sqrt(Math.pow(getWidth(), 2) + Math.pow(getHeight(), 2))) {
            throw new IllegalArgumentException("Display diagonal can not be greater than the hypotenuse" +
                    " of height and width of the tablet");
        }

        this.displayInches = displayInches;
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

    public double getFlashMemoryCapacity() {
        return flashMemoryCapacity;
    }

    /**
     * Mutator for flash memory capacity.
     *
     * @param flashMemoryCapacity New flash memory capacity. Cannot be negative.
     */
    public void setFlashMemoryCapacity(double flashMemoryCapacity) {

        if (flashMemoryCapacity < 0) {
            throw new IllegalArgumentException("Flash memory capacity can not be negative");
        }

        this.flashMemoryCapacity = flashMemoryCapacity;
    }

    public String getColor() {
        return color;
    }

    /**
     * Mutator for color.
     *
     * @param color New color. Cannot be null.
     */
    public void setColor(String color) {
        Objects.requireNonNull(color, "Color description can not be null");
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TabletPC tabletPC = (TabletPC) o;
        return Double.compare(tabletPC.displayInches, displayInches) == 0 && memoryRom == tabletPC.memoryRom && Double.compare(tabletPC.flashMemoryCapacity, flashMemoryCapacity) == 0 && color.equals(tabletPC.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), displayInches, memoryRom, flashMemoryCapacity, color);
    }

    @Override
    public String toString() {
        return "TabletPC{" +
                "weight=" + getWeight() +
                ", width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", batteryCapacity=" + getBatteryCapacity() +
                ", displayInches=" + displayInches +
                ", memoryRom=" + memoryRom +
                ", flashMemoryCapacity=" + flashMemoryCapacity +
                ", color='" + color + '\'' +
                '}';
    }
}
