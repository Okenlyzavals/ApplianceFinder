package by.epam.training.task02.entity.criteria;

import java.util.Objects;

/**
 * Implementation of ParameterValue interface.
 * Defines range of values.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class Range<T extends Comparable<T>> implements ParameterValue<T> {

    /**
     * Minimal value of a range.
     */
    private T minimalValue;
    /**
     * Maximal value of a range.
     */
    private T maximalValue;

    /**
     * Constructor for instances of this class.
     * Values can not be null.
     * Swaps minimal and maximal values if their order is messed up.
     *
     * @param minimalValue Minimal value of a range.
     * @param maximalValue Maximal value of a range.
     */
    public Range(T minimalValue, T maximalValue) {
        Objects.requireNonNull(minimalValue, "Value can not be null");
        Objects.requireNonNull(maximalValue, "Value can not be null");

        if (minimalValue.compareTo(maximalValue) > 0) {
            T temp = maximalValue;
            maximalValue = minimalValue;
            minimalValue = temp;
        }

        this.minimalValue = minimalValue;
        this.maximalValue = maximalValue;
    }

    /**
     * Checks if value is within range.
     *
     * @param value The value to check.
     * @return {@code true} if value is within range, {@code false} otherwise.
     */
    @Override
    public boolean isSatisfactory(Object value) {

        if (value == null || !(value.getClass().equals(getType()))) {
            return false;
        }

        T val = (T) value;
        return (val.compareTo(maximalValue) <= 0) && (val.compareTo(minimalValue) >= 0);

    }

    /**
     * Checks if instance of SingularValue is within range.
     *
     * @param singularValue SingularValue object to check.
     * @return {@code true} if SingularValue object is within range, {@code false} otherwise.
     * @see SingularValue
     */
    public boolean isSatisfactory(SingularValue<?> singularValue) {
        if (singularValue.getType() != getType()) {
            return false;
        }

        return isSatisfactory(singularValue.getValue());
    }

    /**
     * Checks if another instance of Range is within this range.
     *
     * @param range Range object to check.
     * @return {@code true} if both maximal and minimal values of another range
     * are within this range, {@code false} otherwise.
     */
    public boolean isSatisfactory(Range<?> range) {
        if (range.equals(this)) {
            return true;
        }
        if (range.getType() != getType()) {
            return false;
        }

        return (isSatisfactory(range.minimalValue) && isSatisfactory(range.maximalValue));
    }

    @Override
    public Class<?> getType() {
        return minimalValue.getClass();
    }

    public T getMinimalValue() {
        return minimalValue;
    }

    public void setMinimalValue(T minimalValue) {
        this.minimalValue = minimalValue;
    }

    public T getMaximalValue() {
        return maximalValue;
    }

    public void setMaximalValue(T maximalValue) {
        this.maximalValue = maximalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range<?> range = (Range<?>) o;
        return Objects.equals(minimalValue, range.minimalValue) && Objects.equals(maximalValue, range.maximalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minimalValue, maximalValue);
    }

    @Override
    public String toString() {
        return "Range{" +
                "minimalValue=" + minimalValue +
                ", maximalValue=" + maximalValue +
                '}';
    }
}
