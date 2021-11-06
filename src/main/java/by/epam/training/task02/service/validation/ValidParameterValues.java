package by.epam.training.task02.service.validation;

import by.epam.training.task02.dao.config.ApplianceParamValuesConfigParser;
import by.epam.training.task02.entity.criteria.SearchParameter;

/**
 * Service class that provides functionality to validate
 * if parameters values are within appropriate range.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see ApplianceParamValuesConfigParser
 */
final class ValidParameterValues {

    /**
     * This class is not supposed to be instantiated
     */
    private ValidParameterValues() {}

    /**
     * Checks if parameter has its value belonging to acceptable range.
     *
     * @param parameter The parameter to check.
     * @return {@code true} if parameters value is within acceptable range, {@code false} otherwise.
     */
    public static boolean isWithinAcceptableRange(SearchParameter parameter) {

        return ApplianceParamValuesConfigParser.isWithinAcceptableRange(parameter);
    }


}
