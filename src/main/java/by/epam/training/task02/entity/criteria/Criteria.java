package by.epam.training.task02.entity.criteria;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class that represents search criteria.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class Criteria implements Serializable {

    /**
     * Name of criteria. May be useful for logging, but otherwise worthless.
     */
    private String groupSearchName;
    /**
     * A set of SearchParameter objects; each one of them consists of parameters name and value.
     *
     * @see SearchParameter
     */
    private Set<SearchParameter> parameters = new HashSet<>();

    /**
     * Default constructor.
     */
    public Criteria() {
        this("Unnamed criteria");
    }

    /**
     * Constructor that accepts the name of criteria.
     *
     * @param groupSearchName Name of criteria.
     */
    public Criteria(String groupSearchName) {
        this.groupSearchName = groupSearchName;
    }

    /**
     * Adds one or more parameters to criteria.
     *
     * @param parameters Parameter (variable number) to add.
     */
    public void add(SearchParameter... parameters) {
        this.parameters.addAll(Arrays.asList(parameters));
    }

    /**
     * This method returns a set of Class objects.
     * This set will contain Classes of parameters' names,
     * which are usually represented by CriteriaParameterName objects.
     *
     * @return A set of parameters' names' classes.
     * @see by.epam.training.task02.entity.criteria.SearchCriteria.CriteriaParameterName
     */
    public Set<Class<?>> getParametersNameClasses() {
        Set<Class<?>> classes = new HashSet<>();

        for (SearchParameter parameter : parameters) {
            classes.add(parameter.getName().getClass());
        }

        return classes;
    }

    public Set<SearchParameter> getParameters() {
        return parameters;
    }

    public String getGroupSearchName() {
        return groupSearchName;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "groupSearchName='" + groupSearchName + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criteria criteria = (Criteria) o;
        return Objects.equals(groupSearchName, criteria.groupSearchName) && Objects.equals(parameters, criteria.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupSearchName, parameters);
    }
}
