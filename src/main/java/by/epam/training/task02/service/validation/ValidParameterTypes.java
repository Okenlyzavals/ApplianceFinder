package by.epam.training.task02.service.validation;

import by.epam.training.task02.dao.config.ApplianceParamTypesConfigParser;
import by.epam.training.task02.entity.criteria.SearchParameter;

/**
 * Service class that provides functionality to validate if parameters value type is valid.
 * Parameter types are specified within config file.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see ApplianceParamTypesConfigParser
 */
final class ValidParameterTypes {

    /**
     * This class is not supposed to be instantiated
     */
    private ValidParameterTypes() {}

    /**
     * Checks if parameter has a valid value type.
     *
     * @param parameter The parameter to check.
     * @return {@code true} if parameters value type is valid, {@code false} otherwise.
     */
    static boolean isValid(SearchParameter parameter) {
        return ApplianceParamTypesConfigParser.isValid(parameter);
    }

}
