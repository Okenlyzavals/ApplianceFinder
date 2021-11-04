package by.epam.training.task02.entity;

import java.util.Objects;

/**
 * Defines a refrigerator.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class Refrigerator extends WiredAppliance {

    private double freezerCapacityInLiters;
    private double overallCapacityInLiters;

    public Refrigerator() {
        super();
    }

    /**
     * Constructor with parameters.
     * Params can not be negative.
     *
     * @param weight                  Weight of an appliance, preferably in kilos.
     * @param width                   Width of an appliance, preferably in cm.
     * @param height                  Height of an appliance, preferably in cm.
     * @param depth                   Depth of an appliance, preferably in cm.
     * @param powerConsumption        Power consumption of an appliance, preferably in watt.
     * @param freezerCapacityInLiters Capacity of a freezer, in liters.
     *                                Cannot be greater than overall capacity.
     * @param overallCapacityInLiters Overall of a refrigerator, in liters.
     *                                Cannot be greater than total volume.
     */
    public Refrigerator(double weight, double width, double height, double depth, int powerConsumption, double freezerCapacityInLiters, double overallCapacityInLiters) {
        super(weight, width, height, depth, powerConsumption);

        if (freezerCapacityInLiters < 0) {
            throw new IllegalArgumentException("Freezer capacity can not be negative");
        }
        if (overallCapacityInLiters < 0) {
            throw new IllegalArgumentException("Overall capacity can not be negative");
        }
        if (freezerCapacityInLiters > overallCapacityInLiters) {
            throw new IllegalArgumentException("Freezer capacity can not be greater than overall capacity");
        }
        if ((overallCapacityInLiters * 1000) > (height * width * depth)) {
            throw new IllegalArgumentException("Overall capacity can not be greater than total volume");
        }

        this.freezerCapacityInLiters = freezerCapacityInLiters;
        this.overallCapacityInLiters = overallCapacityInLiters;
    }

    public double getFreezerCapacityInLiters() {
        return freezerCapacityInLiters;
    }

    /**
     * Mutator for freezer capacity.
     *
     * @param freezerCapacityInLiters New capacity of a freezer.
     *                                Cannot be negative or greater than overall capacity.
     */
    public void setFreezerCapacityInLiters(double freezerCapacityInLiters) {

        if (freezerCapacityInLiters < 0) {
            throw new IllegalArgumentException("Freezer capacity can not be negative");
        }
        if (freezerCapacityInLiters > overallCapacityInLiters) {
            throw new IllegalArgumentException("Freezer capacity can not be greater than overall capacity");
        }

        this.freezerCapacityInLiters = freezerCapacityInLiters;
    }

    public double getOverallCapacityInLiters() {
        return overallCapacityInLiters;
    }

    /**
     * Mutator for overall capacity.
     *
     * @param overallCapacityInLiters New overall capacity of a refrigerator.
     *                                Cannot be negative, less than freezer capacity,
     *                                or greater than total volume.
     */
    public void setOverallCapacityInLiters(double overallCapacityInLiters) {

        if (overallCapacityInLiters < 0) {
            throw new IllegalArgumentException("Overall capacity can not be negative");
        }
        if (overallCapacityInLiters < freezerCapacityInLiters) {
            throw new IllegalArgumentException("Overall capacity can not be lesser than freezer capacity");
        }
        if ((overallCapacityInLiters * 1000) > (getHeight() * getWidth() * getDepth())) {
            throw new IllegalArgumentException("Overall capacity can not be greater than total volume");
        }

        this.overallCapacityInLiters = overallCapacityInLiters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Refrigerator that = (Refrigerator) o;
        return Double.compare(that.freezerCapacityInLiters, freezerCapacityInLiters) == 0 && Double.compare(that.overallCapacityInLiters, overallCapacityInLiters) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), freezerCapacityInLiters, overallCapacityInLiters);
    }

    @Override
    public String toString() {
        return "Refrigerator{" +
                "weight=" + getWeight() +
                ", width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", powerConsumption=" + getPowerConsumption() +
                ", freezerCapacityInLiters=" + freezerCapacityInLiters +
                ", overallCapacityInLiters=" + overallCapacityInLiters +
                '}';
    }
}
