package by.epam.training.task02.entity;

import java.util.Objects;

/**
 * Abstract class that defines a wireless appliance.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public abstract class BatteryPoweredAppliance extends Appliance {

    private double batteryCapacity;

    public BatteryPoweredAppliance() {
        super();
    }

    /**
     * Constructor with parameters.
     * Params can not be negative.
     *
     * @param weight          Weight of an appliance, preferably in kilos.
     * @param width           Width of an appliance, preferably in cm.
     * @param height          Height of an appliance, preferably in cm.
     * @param depth           Depth of an appliance, preferably in cm.
     * @param batteryCapacity Battery capacity of an appliance, preferably in hours.
     */
    public BatteryPoweredAppliance(double weight, double width, double height, double depth, double batteryCapacity) {
        super(weight, width, height, depth);

        if (batteryCapacity < 0) {
            throw new IllegalArgumentException("Battery capacity can not be negative");
        }

        this.batteryCapacity = batteryCapacity;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    /**
     * Sets the battery capacity.
     *
     * @param batteryCapacity New value of battery capacity. Cannot be negative.
     */
    public void setBatteryCapacity(double batteryCapacity) {

        if (batteryCapacity < 0) {
            throw new IllegalArgumentException("Battery capacity can not be negative");
        }

        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BatteryPoweredAppliance that = (BatteryPoweredAppliance) o;
        return Double.compare(that.batteryCapacity, batteryCapacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), batteryCapacity);
    }

    @Override
    public String toString() {
        return "BatteryPoweredAppliance{" +
                "weight=" + getWeight() +
                ", width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", batteryCapacity=" + batteryCapacity +
                '}';
    }
}
