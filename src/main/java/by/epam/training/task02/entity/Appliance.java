package by.epam.training.task02.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract class that defines an appliance;
 * contains general parameters that are inherent in every appliance known to man.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public abstract class Appliance implements Serializable {

    private double weight;
    private double width;
    private double height;
    private double depth;

    public Appliance() {
    }

    /**
     * Constructor with parameters.
     * Params can not be negative.
     *
     * @param weight Weight of an appliance, preferably in kilos.
     * @param width  Width of an appliance, preferably in cm.
     * @param height Height of an appliance, preferably in cm.
     * @param depth  Depth of an appliance, preferably in cm.
     */
    public Appliance(double weight, double width, double height, double depth) {

        if (weight < 0) {
            throw new IllegalArgumentException("Weight can not be negative");
        }

        if (width < 0) {
            throw new IllegalArgumentException("Width can not be negative");
        }

        if (height < 0) {
            throw new IllegalArgumentException("Height can not be negative");
        }

        if (depth < 0) {
            throw new IllegalArgumentException("Depth can not be negative");
        }

        this.weight = weight;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight can not be negative");
        }

        this.weight = weight;
    }

    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of appliance.
     *
     * @param width New width. Cannot be negative.
     */
    public void setWidth(double width) {
        if (width < 0) {
            throw new IllegalArgumentException("Width can not be negative");
        }

        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of appliance.
     *
     * @param height New height. Cannot be negative.
     */
    public void setHeight(double height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height can not be negative");
        }

        this.height = height;
    }

    public double getDepth() {
        return depth;
    }

    /**
     * Sets the depth of appliance.
     *
     * @param depth New depth. Cannot be negative.
     */
    public void setDepth(double depth) {
        if (depth < 0) {
            throw new IllegalArgumentException("Depth can not be negative");
        }

        this.depth = depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appliance appliance = (Appliance) o;
        return Double.compare(appliance.weight, weight) == 0 && Double.compare(appliance.width, width) == 0 && Double.compare(appliance.height, height) == 0 && Double.compare(appliance.depth, depth) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, width, height, depth);
    }

    @Override
    public String toString() {
        return "Appliance{" +
                "weight=" + weight +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                '}';
    }
}
