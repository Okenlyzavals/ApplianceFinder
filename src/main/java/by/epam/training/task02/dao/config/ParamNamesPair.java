package by.epam.training.task02.dao.config;

import java.util.Arrays;

/**
 * Utility class that is designed to store the Class name of a parameter and its own name.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
final class ParamNamesPair {

    /**
     * Array of String that stores class name and own name
     */
    private String[] namePair = new String[2];

    /**
     * Constructor for class {@code ParamNamesPair}
     *
     * @param paramClassName Class name of a parameter
     * @param paramName      Own name of a parameter
     */
    ParamNamesPair(String paramClassName, String paramName) {
        namePair[0] = paramClassName;
        namePair[1] = paramName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParamNamesPair that = (ParamNamesPair) o;
        return Arrays.equals(namePair, that.namePair);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(namePair);
    }

    @Override
    public String toString() {
        return "ParamNamePair{" +
                "namePair=" + Arrays.toString(namePair) +
                '}';
    }
}
