package by.epam.training.task02.dao.util;

import by.epam.training.task02.dao.xmlreadcommand.ApplianceReadCommandXML;
import by.epam.training.task02.dao.xmlreadcommand.XMLReadCommandProvider;
import by.epam.training.task02.entity.criteria.Criteria;
import by.epam.training.task02.entity.criteria.ParameterValue;
import by.epam.training.task02.entity.criteria.SearchCriteria;
import by.epam.training.task02.entity.criteria.SearchParameter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility class for final handling of Criteria object,
 * preceding data access.
 *
 * @author Baranovsky E.K.
 * @version 1.0
 */
public final class CriteriaDAOUtil {

    /**
     * Locates {@code SearchParameter} objects with duplicate names
     * but different values and unites them into one.
     * <p>This may be required if user wants to find appliances that satisfy either of criterias' values,
     * for example, Laptop with operational system "Windows" OR "Linux"
     * (both are added to criteria as instances of {@code SingularValue}).
     *
     * @param criteria The criteria to modify.
     */
    public static void uniteDuplicateParamsIntoMultiValues(Criteria criteria) {

        Set<SearchCriteria.CriteriaParameterName> duplicateParameterNames = getDuplicateParameterNamesSet(criteria);

        if (duplicateParameterNames.isEmpty()) {
            return;
        }

        for (SearchCriteria.CriteriaParameterName duplicateParameterName : duplicateParameterNames) {
            List<SearchParameter> duplicateParameters = getParametersFromCriteriaByName(duplicateParameterName, criteria);

            List<ParameterValue<?>> duplicateValuesList = new ArrayList<>();

            for (SearchParameter duplicateParameter : duplicateParameters) {
                duplicateValuesList.add(duplicateParameter.getValue());
            }

            criteria.getParameters().removeIf(e -> e.getName().equals(duplicateParameterName));

            MultiValue multiValue = new MultiValue(duplicateValuesList);
            criteria.add(new SearchParameter(duplicateParameterName, multiValue));
        }
    }

    /**
     * Counts how many times did a parameter with specified name appear within a criteria.
     *
     * @param name     The name of parameter to check.
     * @param criteria The search criteria.
     * @return The number of appearances of a parameter with given name within a criteria.
     */
    private static int calculateParamNameAppearancesNumber(SearchCriteria.CriteriaParameterName name, Criteria criteria) {
        int count = 0;

        for (SearchParameter parameter : criteria.getParameters()) {
            if (parameter.getName().equals(name)) {
                count++;
            }
        }
        return count;
    }

    /**
     * @param criteria The criteria to check.
     * @return Set of {@code CriteriaParameterName} - names of parameters of a criterion.
     */
    private static Set<SearchCriteria.CriteriaParameterName> getParameterNamesSet(Criteria criteria) {
        Set<SearchCriteria.CriteriaParameterName> paramNamesSet = new HashSet<>();

        for (SearchParameter parameter : criteria.getParameters()) {
            paramNamesSet.add(parameter.getName());
        }

        return paramNamesSet;
    }

    /**
     * @param criteria The criteria to check.
     * @return Set of {@code CriteriaParameterName} - names of parameters that have identical names.
     */
    private static Set<SearchCriteria.CriteriaParameterName> getDuplicateParameterNamesSet(Criteria criteria) {

        Set<SearchCriteria.CriteriaParameterName> paramNamesSet = getParameterNamesSet(criteria);

        if (paramNamesSet.size() == criteria.getParameters().size()) {
            return new HashSet<>();
        }

        Set<SearchCriteria.CriteriaParameterName> duplicateParameterNames = new HashSet<>();

        for (SearchParameter parameter : criteria.getParameters()) {
            if (calculateParamNameAppearancesNumber(parameter.getName(), criteria) > 1) {
                duplicateParameterNames.add(parameter.getName());
            }
        }

        return duplicateParameterNames;
    }

    /**
     * @param name     Name of parameter.
     * @param criteria The criteria to search in.
     * @return List of {@code SearchParameter} - list of parameters with specified name.
     */
    private static List<SearchParameter> getParametersFromCriteriaByName(SearchCriteria.CriteriaParameterName name, Criteria criteria) {
        List<SearchParameter> result = new ArrayList<>();

        for (SearchParameter parameter : criteria.getParameters()) {
            if (parameter.getName().equals(name)) {
                result.add(parameter);
            }
        }

        return result;
    }

    /**
     * Gets command that matches Criteria.
     * <p> At this moment within a program,
     * Criteria object may contain parameters of no more than 3 types:
     * <ul>
     *     <li> {@code SearchCriteria.AnyAppliance}
     *     <li> One of {@code SearchCriteria.BatteryPoweredAppliance} and {@code SearchCriteria.WiredAppliance}
     *     <li> One of other {@code SearchCriteria} types.
     * </ul>
     * But it may not contain all of them at once.
     * For example, a criteria of
     * AnyAppliance will need {xmlreadcommand.AnyApplianceReadCommandXML},
     * criteria of AnyAppliance+WiredAppliance will need {xmlreadcommand.WiredApplianceReadCommandXML},
     * criteria of AnyAppliance+WiredAppliance+Laptop will need
     * {@code xmlreadcommand.LaptopReadCommandXML}.
     * This method resolves this issue.
     *
     * @param criteria The criteria to get command for.
     * @return Implementation of    {@code ApplianceReadCommandXML} suited for specified criteria.
     * {@code WrongRequestReadCommandXML} if there are no such commands.
     * @see XMLReadCommandProvider
     */
    public static ApplianceReadCommandXML getMatchingCommand(Criteria criteria) {
        XMLReadCommandProvider provider = XMLReadCommandProvider.getInstance();
        Set<Class<?>> criteriaClassesSet = criteria.getParametersNameClasses();

        Class<?>[] generalClassesData = {SearchCriteria.AnyAppliance.class, SearchCriteria.BatteryPoweredAppliance.class, SearchCriteria.WiredAppliance.class};
        Set<Class<?>> generalClasses = new HashSet<>(List.of(generalClassesData));

        if (!generalClasses.containsAll(criteriaClassesSet)) {
            criteriaClassesSet.removeAll(generalClasses);

            if (criteriaClassesSet.size() != 1) {
                return provider.getCommand("Wrong_request");
            }

            List<Class<?>> classesList = new ArrayList<>(criteriaClassesSet);
            return provider.getCommand(classesList.get(0).getSimpleName());
        } else {
            if (criteriaClassesSet.size() == 1) {
                List<Class<?>> classesList = new ArrayList<>(criteriaClassesSet);
                return provider.getCommand((classesList.get(0).getSimpleName()));
            } else {
                criteriaClassesSet.remove(generalClassesData[0]);

                if (criteriaClassesSet.size() != 1) {
                    return provider.getCommand("Wrong_request");
                }

                List<Class<?>> classesList = new ArrayList<>(criteriaClassesSet);
                return provider.getCommand((classesList.get(0).getSimpleName()));
            }
        }
    }

    /**
     * A static inner class that defines another kind of ParameterValue
     * other than Range and SingularValue. It includes multiple other
     * ParameterValue objects.
     *
     * @param <T>
     * @author Baranovsky E. K.
     * @version 1.0
     */
    private static class MultiValue<T extends Comparable<T>> implements ParameterValue<T> {

        private List<ParameterValue<T>> valueList = new ArrayList<>();

        MultiValue(List<ParameterValue<T>> value) {
            valueList.addAll(value);
        }

        /**
         * @param value The value to check.
         * @return {@code true} if value satisfies any of parameterValues in valueList;
         * {@code false} otherwise.
         */
        @Override
        public boolean isSatisfactory(Object value) {

            for (ParameterValue<T> listValue : valueList) {
                if (listValue.isSatisfactory(value)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Class<?> getType() {
            return valueList.get(0).getType();
        }
    }

}
