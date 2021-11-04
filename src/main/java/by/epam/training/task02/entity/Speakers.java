package by.epam.training.task02.entity;

import java.util.Objects;

/**
 * Defines speakers.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class Speakers extends WiredAppliance {

    private int numberOfSpeakers;
    private double minimalFrequency;
    private double maximalFrequency;
    private double cordLengthInMeters;

    public Speakers() {
        super();
    }

    /**
     * Constructor with parameters.
     * Params cannot be negative.
     * Values of minimal and maximal frequencies
     * are swapped if the former is greater than the latter.
     *
     * @param weight             Weight of an appliance, preferably in kilos.
     * @param width              Width of an appliance, preferably in cm.
     * @param height             Height of an appliance, preferably in cm.
     * @param depth              Depth of an appliance, preferably in cm.
     * @param powerConsumption   Power consumption of an appliance, preferably in watt.
     * @param numberOfSpeakers   Number of speakers in set.
     * @param minimalFrequency   Minimal frequency of speakers, in GHz.
     * @param maximalFrequency   Maximal frequency of speakers, in GHz.
     * @param cordLengthInMeters Length of a cord, in meters.
     */
    public Speakers(double weight, double width, double height, double depth, int powerConsumption, int numberOfSpeakers, double minimalFrequency, double maximalFrequency, double cordLengthInMeters) {
        super(weight, width, height, depth, powerConsumption);

        if (numberOfSpeakers < 0) {
            throw new IllegalArgumentException("Number of speakers can not be negative");
        }
        if (minimalFrequency < 0) {
            throw new IllegalArgumentException("Minimal frequency can not be negative");
        }
        if (maximalFrequency < 0) {
            throw new IllegalArgumentException("Maximal frequency can not be negative");
        }
        if (cordLengthInMeters < 0) {
            throw new IllegalArgumentException("Cord length can not be negative");
        }
        if (minimalFrequency > maximalFrequency) {
            double temp = minimalFrequency;
            minimalFrequency = maximalFrequency;
            maximalFrequency = temp;
        }

        this.numberOfSpeakers = numberOfSpeakers;
        this.minimalFrequency = minimalFrequency;
        this.maximalFrequency = maximalFrequency;
        this.cordLengthInMeters = cordLengthInMeters;
    }

    public int getNumberOfSpeakers() {
        return numberOfSpeakers;
    }

    /**
     * Mutator for number of speakers.
     *
     * @param numberOfSpeakers New number of speakers in set. Cannot be negative.
     */
    public void setNumberOfSpeakers(int numberOfSpeakers) {

        if (numberOfSpeakers < 0) {
            throw new IllegalArgumentException("Number of speakers can not be negative");
        }

        this.numberOfSpeakers = numberOfSpeakers;
    }


    public double getMinimalFrequency() {
        return minimalFrequency;
    }

    /**
     * Mutator for minimal frequency.
     *
     * @param minimalFrequency New minimal frequency.
     *                         Cannot be negative or greater than maximal frequency.
     */
    public void setMinimalFrequency(double minimalFrequency) {

        if (minimalFrequency < 0) {
            throw new IllegalArgumentException("Minimal frequency can not be negative");
        }
        if (minimalFrequency > maximalFrequency) {
            throw new IllegalArgumentException("Minimal frequency can not be greater than maximal frequency");
        }

        this.minimalFrequency = minimalFrequency;
    }

    public double getMaximalFrequency() {
        return maximalFrequency;
    }

    /**
     * Mutator for maximal frequency.
     *
     * @param maximalFrequency New maximal frequency.
     *                         Cannot be negative or less than minimal frequency.
     */
    public void setMaximalFrequency(double maximalFrequency) {

        if (maximalFrequency < 0) {
            throw new IllegalArgumentException("Maximal frequency can not be negative");
        }
        if (minimalFrequency > maximalFrequency) {
            throw new IllegalArgumentException("Maximal frequency can not be less than minimal frequency");
        }

        this.maximalFrequency = maximalFrequency;
    }

    public double getCordLengthInMeters() {
        return cordLengthInMeters;
    }

    /**
     * Mutator for cord length.
     *
     * @param cordLengthInMeters New cord length.
     *                           Cannot be negative.
     */
    public void setCordLengthInMeters(double cordLengthInMeters) {

        if (cordLengthInMeters < 0) {
            throw new IllegalArgumentException("Cord length can not be negative");
        }

        this.cordLengthInMeters = cordLengthInMeters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Speakers speakers = (Speakers) o;
        return numberOfSpeakers == speakers.numberOfSpeakers && Double.compare(speakers.minimalFrequency, minimalFrequency) == 0 && Double.compare(speakers.maximalFrequency, maximalFrequency) == 0 && Double.compare(speakers.cordLengthInMeters, cordLengthInMeters) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfSpeakers, minimalFrequency, maximalFrequency, cordLengthInMeters);
    }

    @Override
    public String toString() {
        return "Speakers{" +
                "weight=" + getWeight() +
                ", width=" + getWidth() +
                ", height=" + getHeight() +
                ", depth=" + getDepth() +
                ", powerConsumption=" + getPowerConsumption() +
                ", numberOfSpeakers=" + numberOfSpeakers +
                ", minimalFrequency=" + minimalFrequency +
                ", maximalFrequency=" + maximalFrequency +
                ", cordLengthInMeters=" + cordLengthInMeters +
                '}';
    }
}
