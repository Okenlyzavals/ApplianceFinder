package by.epam.training.task02.service.validation;

import by.epam.training.task02.dao.config.ApplianceParamValuesConfigParser;
import by.epam.training.task02.entity.criteria.SearchParameter;

/**
 * Utility class that provides functionality to validate
 * if parameters values are within appropriate range.
 * Ranges of values are specified within config file.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
final class ValidParameterValues {

    /**
     * Checks if parameters have its values belonging to acceptable range.
     *
     * @param parameter The parameter to check.
     * @return {@code true} if parameters value is within acceptable range, {@code false} otherwise.
     */
    public static boolean isWithinAcceptableRange(SearchParameter parameter) {

        return ApplianceParamValuesConfigParser.isWithinAcceptableRange(parameter);
    }


}
