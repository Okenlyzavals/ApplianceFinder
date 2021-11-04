package by.epam.training.task02.entity;

import java.util.Objects;

/**
 * Abstract class that defines a wired appliance.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public abstract class WiredAppliance extends Appliance {

    private int powerConsumption;

    public WiredAppliance() {
        super();
    }

    /**
     * Constructor with parameters.
     * Params can not be negative.
     *
     * @param weight           Weight of an appliance, preferably in kilos.
     * @param width            Width of an appliance, preferably in cm.
     * @param height           Height of an appliance, preferably in cm.
     * @param depth            Depth of an appliance, preferably in cm.
     * @param powerConsumption Power consumption of an appliance, preferably in watt.
     */
    public WiredAppliance(double weight, double width, double height, double depth, int powerConsumption) {
        super(weight, width, height, depth);

        if (powerConsumption < 0) {
            throw new IllegalArgumentException("Power consumption capacity can not be negative");
        }

        this.powerConsumption = powerConsumption;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    /**
     * Sets the power consumption.
     *
     * @param powerConsumption New value of power consumption. Cannot be negative.
     */
    public void setPowerConsumption(int powerConsumption) {

        if (powerConsumption < 0) {
            throw new IllegalArgumentException("Power consumption capacity can not be negative");
        }

        this.powerConsumption = powerConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WiredAppliance that = (WiredAppliance) o;
        return powerConsumption == that.powerConsumption;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), powerConsumption);
    }

    @Override
    public String toString() {
        return "WiredAppliance{" +
                "weight=" + getWeight() +
                ", width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", powerConsumption=" + powerConsumption +
                '}';
    }

}
