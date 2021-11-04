package by.epam.training.task02.service.validation;

import by.epam.training.task02.entity.criteria.Criteria;
import by.epam.training.task02.entity.criteria.SearchCriteria;
import by.epam.training.task02.entity.criteria.SearchParameter;

import java.util.*;

/**
 * Class that provides functionality to validate the criteria before passing it down to DAL
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class Validator {

    /**
     * A set that incorporates the power types of appliances
     */
    private static final Set<Class<?>> APPLIANCE_POWER_TYPES = new HashSet<>();
    /**
     * A map that contains classes of appliances stored by their power type
     */
    private static final Map<Class<? extends SearchCriteria.CriteriaParameterName>, List<Class<?>>>
            APPLIANCE_CLASSES_BY_POWER_TYPES = new HashMap<>();

    static {
        initPowerTypes();
        initApplianceClassesByPowerTypes();
    }

    /**
     * Is used to validate the criteria.
     *
     * @param criteria The criteria to be validated.
     * @return {@code true} if criteria is valid, {@code false} otherwise.
     * @see Criteria
     */
    public static boolean validate(Criteria criteria) {

        if (isCriteriaBlank(criteria)) {
            return false;
        } else if (!areCriteriaParametersValid(criteria)) {
            return false;
        } else if (!areCriteriaParameterTypesValid(criteria)) {
            return false;
        } else if (!arePowerTypeParametersCompatible(criteria)) {
            return false;
        } else if (doesCriteriaReferenceMoreThanOneNonGeneralParameterType(criteria)) {
            return false;
        } else if (!doPowerTypeAndApplianceTypeParametersMatch(criteria)) {
            return false;
        } else return areParametersValuesWithinAppropriateRange(criteria);
    }

    /**
     * Checks if criteria is null or if it has no parameters.
     *
     * @param criteria The criteria to be checked.
     * @return {@code true} if criteria is not null and has parameters, {@code false} otherwise.
     */
    private static boolean isCriteriaBlank(Criteria criteria) {
        if (criteria == null) {
            return true;
        }

        return criteria.getParameters().isEmpty();
    }

    /**
     * Checks if parameters of criteria are not null and their contents are not null.
     *
     * @param criteria The criteria to be checked.
     * @return {@code true} if parameters and their contents are not null, {@code false} otherwise.
     */
    private static boolean areCriteriaParametersValid(Criteria criteria) {

        for (SearchParameter parameter : criteria.getParameters()) {
            if (parameter == null || parameter.getName() == null || parameter.getValue() == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if parameters of criteria all have valid types.
     *
     * @param criteria The criteria to be checked.
     * @return {@code true} if parameters have valid types, {@code false} otherwise.
     * @see ValidParameterTypes
     */
    private static boolean areCriteriaParameterTypesValid(Criteria criteria) {

        for (SearchParameter parameter : criteria.getParameters()) {
            if (!ValidParameterTypes.isValid(parameter)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if there are no conflicting power type parameters
     * <p> An appliance can not be wired and battery powered simultaneously.
     * Thus, such kinds of criteria are forbidden.
     *
     * @param criteria The criteria to be checked.
     * @return {@code true} if there are no incompatible parameters, {@code false} otherwise.
     */
    private static boolean arePowerTypeParametersCompatible(Criteria criteria) {
        Set<Class<?>> paramsClassesSet = criteria.getParametersNameClasses();

        return !(paramsClassesSet.contains(SearchCriteria.WiredAppliance.class) && paramsClassesSet.contains(SearchCriteria.BatteryPoweredAppliance.class));
    }

    /**
     * Checks if parameters in criteria reference more than one specific appliance
     * <p>A criteria might reference more than one appliance type:
     * for example, it can include parameters of both Oven and Refrigerator,
     * which will require the DAL to find an appliance that is Oven and Refrigerator at the same time.
     * As this is logically impossible, such kinds of criterion are forbidden.
     * This, however, does not apply to general parameters, such as weight, height, power consumption, etc.
     * Types of such general parameters are {@code AnyAppliance}, {@code BatteryPoweredAppliance},
     * and {@code WiredAppliance}
     *
     * @param criteria The criteria to be checked.
     * @return {@code true} if criteria references more than one non-general type,
     * {@code false} otherwise.
     * @see by.epam.training.task02.entity.criteria.SearchCriteria.AnyAppliance
     * @see by.epam.training.task02.entity.criteria.SearchCriteria.BatteryPoweredAppliance
     * @see by.epam.training.task02.entity.criteria.SearchCriteria.WiredAppliance
     */
    private static boolean doesCriteriaReferenceMoreThanOneNonGeneralParameterType(Criteria criteria) {

        Set<Class<?>> paramsClassesSet = criteria.getParametersNameClasses();

        paramsClassesSet.removeIf(e -> (e.equals(SearchCriteria.AnyAppliance.class)
                || APPLIANCE_POWER_TYPES.contains(e)));

        return (paramsClassesSet.size() > 1);
    }

    /**
     * Checks if power type (if criteria has any) parameter
     * matches the non-general appliance parameter (if criteria has any).
     *
     * @param criteria The criteria to be checked.
     * @return {@code true} if power type parameter and specific appliance type parameter match,
     * {@code false} otherwise.
     * @see Validator#APPLIANCE_POWER_TYPES
     * @see Validator#APPLIANCE_CLASSES_BY_POWER_TYPES
     */
    private static boolean doPowerTypeAndApplianceTypeParametersMatch(Criteria criteria) {
        Set<Class<?>> paramsClassesSet = criteria.getParametersNameClasses();

        Class<?> currentPowerTypeClass = null;
        for (Class<?> powerType : APPLIANCE_POWER_TYPES) {
            if (paramsClassesSet.contains(powerType)) {
                currentPowerTypeClass = powerType;
            }
        }

        paramsClassesSet.removeIf(e -> (e.equals(SearchCriteria.AnyAppliance.class)
                || APPLIANCE_POWER_TYPES.contains(e)));

        if (!paramsClassesSet.isEmpty() && currentPowerTypeClass != null) {
            for (Class<?> applianceType : paramsClassesSet) {
                if (!APPLIANCE_CLASSES_BY_POWER_TYPES.get(currentPowerTypeClass).contains(applianceType)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if parameters of criteria have their values within range allowed in config.
     *
     * @param criteria The criteria to be checked.
     * @return {@code true} if parameters are within acceptable range, {@code false} otherwise.
     * @see ValidParameterValues
     */
    private static boolean areParametersValuesWithinAppropriateRange(Criteria criteria) {

        for (SearchParameter parameter : criteria.getParameters()) {
            if (!ValidParameterValues.isWithinAcceptableRange(parameter)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Initializes {@code APPLIANCE_POWER_TYPES}
     *
     * @see Validator#APPLIANCE_POWER_TYPES
     */
    private static void initPowerTypes() {
        APPLIANCE_POWER_TYPES.add(SearchCriteria.WiredAppliance.class);
        APPLIANCE_POWER_TYPES.add(SearchCriteria.BatteryPoweredAppliance.class);
    }

    /**
     * Initializes {@code APPLIANCE_CLASSES_BY_POWER_TYPES}
     *
     * @see Validator#APPLIANCE_CLASSES_BY_POWER_TYPES
     */
    private static void initApplianceClassesByPowerTypes() {
        List<Class<?>> wiredAppliances = new ArrayList<>();
        wiredAppliances.add(SearchCriteria.Oven.class);
        wiredAppliances.add(SearchCriteria.Refrigerator.class);
        wiredAppliances.add(SearchCriteria.Speakers.class);
        wiredAppliances.add(SearchCriteria.VacuumCleaner.class);
        APPLIANCE_CLASSES_BY_POWER_TYPES.put(SearchCriteria.WiredAppliance.class, wiredAppliances);

        List<Class<?>> batteryPoweredAppliances = new ArrayList<>();
        batteryPoweredAppliances.add(SearchCriteria.Laptop.class);
        batteryPoweredAppliances.add(SearchCriteria.TabletPC.class);
        APPLIANCE_CLASSES_BY_POWER_TYPES.put(SearchCriteria.BatteryPoweredAppliance.class, batteryPoweredAppliances);

    }
}
