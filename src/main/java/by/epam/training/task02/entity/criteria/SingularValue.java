package by.epam.training.task02.entity.criteria;

import java.util.Objects;

/**
 * Implementation of ParameterValue interface.
 * Defines one singular value.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class SingularValue<T extends Comparable<T>> implements ParameterValue<T> {

    /**
     * Value of parameter.
     */
    private T value;

    /**
     * Constructor for instances of this class.
     * Values can not be null.
     *
     * @param value Value to be set.
     */
    public SingularValue(T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        this.value = value;
    }

    @Override
    public boolean isSatisfactory(Object value) {
        if (!value.getClass().equals(getType())) {
            return false;
        }
        return (this.value.equals(value));
    }

    @Override
    public Class<?> getType() {
        return value.getClass();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingularValue<?> that = (SingularValue<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "SingularValue{" +
                "value=" + value +
                '}';
    }
}
