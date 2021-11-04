package by.epam.training.task02.entity.criteria;

import java.io.Serializable;

/**
 * An interface for values of parameters.
 * <p>Implementations of this class are used to define intervals for values,
 * which are used to check if appliances' characteristics fit search criterion.
 *
 * @param <T> This parameter must implement Comparable,
 *            as objects of this class are also used to verify values during the search.
 * @author Baranovsky E. K.
 * @version 1.0
 */
public interface ParameterValue<T extends Comparable<T>> extends Serializable {

    /**
     * Checks if given value lies within the given interval.
     *
     * @param value The value to check.
     * @return {@code true} if value fits the interval
     * {@code false} otherwise.
     */
    boolean isSatisfactory(Object value);

    /**
     * Returns the type of values that are used to define this parameter.
     *
     * @return Instance of Class - type of values which define the parameters' interval.
     */
    Class<?> getType();

}
