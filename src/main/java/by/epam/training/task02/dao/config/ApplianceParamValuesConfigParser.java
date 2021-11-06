package by.epam.training.task02.dao.configparser;

import by.epam.training.task02.entity.criteria.ParameterValue;
import by.epam.training.task02.entity.criteria.Range;
import by.epam.training.task02.entity.criteria.SearchParameter;
import by.epam.training.task02.entity.criteria.SingularValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Provides functionality to read acceptable parameter values from config file
 * and to check if parameters' value is within acceptable range.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public final class ApplianceParamValuesConfigParser {

    /**
     * Path to config file.
     */
    private static final String CONFIG_PATH =
            Objects.requireNonNull(ApplianceParamTypesConfigParser.
                    class.getClassLoader().
                    getResource("paramValueRangesConfig.ini")).getPath();

    /**
     * Map that contains allowed value ranges for numeric parameters.
     *
     * @see ParamNamesPair
     * @see Range
     */
    private static final Map<ParamNamesPair, Range<?>> PARAM_VALUE_RANGES = new HashMap<>();

    static {
        initValueRangesFromConfig();
    }

    /**
     * This class is not supposed to be instantiated.
     */
    private ApplianceParamValuesConfigParser() {
    }

    /**
     * Checks if parameters have its values belonging to acceptable range.
     *
     * @param parameter The parameter to check.
     * @return {@code true} if parameters value is within acceptable range, {@code false} otherwise.
     */
    public static boolean isWithinAcceptableRange(SearchParameter parameter) {
        ParamNamesPair pendingApprovalPair = new ParamNamesPair(parameter.getName().getClass().getSimpleName(), parameter.getName().toString());

        Range<?> rangeForParam = PARAM_VALUE_RANGES.get(pendingApprovalPair);

        if (rangeForParam == null) {
            return true;
        }

        ParameterValue<?> value = parameter.getValue();

        if (value instanceof Range) {
            return rangeForParam.isSatisfactory((Range<?>) value);
        }
        if (value instanceof SingularValue) {
            return rangeForParam.isSatisfactory((SingularValue<?>) value);
        }

        return false;
    }

    /**
     * Gets ranges for parameters from config file.
     * Uses ValidParameterTypes class to get the type of value.
     * It is assumed that config file is formatted correctly.
     */
    private static void initValueRangesFromConfig() {

        try (Scanner scanner = new Scanner(new File(CONFIG_PATH))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                line = line.trim();

                if (line.isBlank() || line.charAt(0) == '!') {
                    continue;
                }

                String[] args = line.split(" ");
                if (args.length != 4) {
                    throw new RuntimeException("Wrong range set in " + CONFIG_PATH);
                }

                ParamNamesPair newPair = new ParamNamesPair(args[0], args[1]);

                Class<?> paramType = ApplianceParamTypesConfigParser
                        .getValidParameterTypes().get(newPair);
                Objects.requireNonNull(paramType, "Inappropriate class in " + CONFIG_PATH);

                if (paramType.equals(Double.class)) {
                    PARAM_VALUE_RANGES.put(newPair, new Range<>(Double.parseDouble(args[2]), Double.parseDouble(args[3])));
                } else if (paramType.equals(Float.class)) {
                    PARAM_VALUE_RANGES.put(newPair, new Range<>(Float.parseFloat(args[2]), Float.parseFloat(args[3])));
                } else if (paramType.equals(Integer.class)) {
                    PARAM_VALUE_RANGES.put(newPair, new Range<>(Integer.parseInt(args[2]), Integer.parseInt(args[3])));
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
