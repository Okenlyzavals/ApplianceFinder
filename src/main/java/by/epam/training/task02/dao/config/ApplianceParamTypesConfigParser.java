package by.epam.training.task02.dao.config;

import by.epam.training.task02.entity.criteria.SearchParameter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Provides functionality to read allowed parameter types from config file
 * and to check if parameter is valid.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public final class ApplianceParamTypesConfigParser {

    /**
     * Path to config file.
     */
    private static final String CONFIG_PATH =
            Objects.requireNonNull(ApplianceParamTypesConfigParser.
                    class.getClassLoader().
                    getResource("validParamTypesConfig.ini")).getPath();

    /**
     * Map that contains valid types for given parameters.
     *
     * @see ParamNamesPair
     */
    private static final Map<ParamNamesPair, Class<?>> VALID_PARAMETER_TYPES = new HashMap<>();

    /**
     * Map that contains types that are allowed for parameters, which are stored by name
     */
    private static final Map<String, Class<?>> VALID_CLASSES = new HashMap<>();

    static {
        initValidClasses();
    }

    static {
        getValidTypesFromConfig();
    }

    /**
     * This class is not supposed to be instantiated.
     */
    private ApplianceParamTypesConfigParser() {
    }

    /**
     * Checks if parameter has a valid value type.
     *
     * @param parameter The parameter to check.
     * @return {@code true} if parameters value type is valid, {@code false} otherwise.
     */
    public static boolean isValid(SearchParameter parameter) {
        ParamNamesPair pendingApprovalPair = new ParamNamesPair(parameter.getName().getClass().getSimpleName(), parameter.getName().toString());

        Class<?> paramClass = VALID_PARAMETER_TYPES.get(pendingApprovalPair);

        return (paramClass != null && (paramClass.equals(parameter.getValue().getType())));
    }

    /**
     * Gets valid types for parameters from config file.
     * It is assumed that config file is formatted correctly.
     */
    private static void getValidTypesFromConfig() {

        try (Scanner scanner = new Scanner(new File(CONFIG_PATH))) {

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                line = line.trim();

                if (line.isBlank() || line.charAt(0) == '!') {
                    continue;
                }

                String[] params = line.split(" ");

                Class<?> paramClass = VALID_CLASSES.get(params[2].toLowerCase(Locale.ROOT));

                VALID_PARAMETER_TYPES.put(new ParamNamesPair(params[0], params[1]), paramClass);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes VALID_CLASSES map.
     *
     * @see ApplianceParamTypesConfigParser#VALID_CLASSES
     */
    private static void initValidClasses() {
        VALID_CLASSES.put("double", Double.class);
        VALID_CLASSES.put("float", Float.class);
        VALID_CLASSES.put("integer", Integer.class);
        VALID_CLASSES.put("int", Integer.class);
        VALID_CLASSES.put("string", String.class);
        VALID_CLASSES.put("str", String.class);
        VALID_CLASSES.put("boolean", Boolean.class);
        VALID_CLASSES.put("bool", Boolean.class);
    }

    /**
     * @return Map of types that are valid for specified parameters.
     * @see ApplianceParamTypesConfigParser#VALID_PARAMETER_TYPES
     */
    public static Map<ParamNamesPair, Class<?>> getValidParameterTypes() {
        return VALID_PARAMETER_TYPES;
    }

}
