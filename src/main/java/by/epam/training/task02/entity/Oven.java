package by.epam.training.task02.entity;

import java.util.Objects;

/**
 * Defines an oven.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class Oven extends WiredAppliance {

    private double capacityInLiters;

    public Oven() {
        super();
    }

    /**
     * Constructor with parameters.
     * Decimal params cannot be negative.
     *
     * @param weight           Weight of an appliance, preferably in kilos.
     * @param width            Width of an appliance, preferably in cm.
     * @param height           Height of an appliance, preferably in cm.
     * @param depth            Depth of an appliance, preferably in cm.
     * @param powerConsumption Power consumption of an appliance, preferably in watt.
     * @param capacityInLiters Capacity of an oven, in liters. Cannot be greater than total volume.
     */
    public Oven(double weight, double width, double height, double depth, int powerConsumption, double capacityInLiters) {
        super(weight, width, height, depth, powerConsumption);

        if (capacityInLiters < 0) {
            throw new IllegalArgumentException("Capacity can not be negative");
        }
        if ((capacityInLiters * 1000) > (height * width * depth)) {
            throw new IllegalArgumentException("Capacity can not be greater than volume");
        }

        this.capacityInLiters = capacityInLiters;
    }

    public double getCapacityInLiters() {
        return capacityInLiters;
    }

    /**
     * Mutator for capacity.
     *
     * @param capacityInLiters New capacity of an oven, in liters.
     *                         Cannot be negative or greater than total volume.
     */
    public void setCapacityInLiters(double capacityInLiters) {

        if (capacityInLiters < 0) {
            throw new IllegalArgumentException("Capacity can not be negative");
        }
        if ((capacityInLiters * 1000) > (getHeight() * getWidth() * getDepth())) {
            throw new IllegalArgumentException("Capacity can not be greater than total volume");
        }

        this.capacityInLiters = capacityInLiters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Oven oven = (Oven) o;
        return Double.compare(oven.capacityInLiters, capacityInLiters) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacityInLiters);
    }

    @Override
    public String toString() {
        return "Oven{" +
                "weight=" + getWeight() +
                ", width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", powerConsumption=" + getPowerConsumption() +
                ", capacity=" + capacityInLiters +
                '}';
    }
}
