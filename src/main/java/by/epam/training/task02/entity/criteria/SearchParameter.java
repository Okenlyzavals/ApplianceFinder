package by.epam.training.task02.entity.criteria;

import java.io.Serializable;
import java.util.Objects;

/**
 * Defines an upper-level parameter for criteria.
 * <p>Instances of that class are passed into criteria and are later used to search objects in DAL.
 * They contain the type of parameter (say, overall capacity of refrigerator)
 * and value that sought Appliance has to fit
 * (again, let's say a Range from 14 to 28 liters).
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see Criteria
 */
public class SearchParameter implements Serializable {

    /**
     * Name (type) of parameter.
     *
     * @see SearchCriteria
     */
    private SearchCriteria.CriteriaParameterName name;
    /**
     * Value of parameter.
     *
     * @see ParameterValue
     */
    private ParameterValue<? extends Comparable<?>> value;

    /**
     * Constructor for instances of this class.
     *
     * @param name  Name of parameter.
     * @param value Value that sought appliance has to fit.
     */
    public SearchParameter(SearchCriteria.CriteriaParameterName name, ParameterValue<? extends Comparable<?>> value) {
        this.name = name;
        this.value = value;
    }

    public SearchCriteria.CriteriaParameterName getName() {
        return name;
    }

    public void setName(SearchCriteria.CriteriaParameterName name) {
        this.name = name;
    }

    public ParameterValue<? extends Comparable<?>> getValue() {
        return value;
    }

    public void setValue(ParameterValue<? extends Comparable<?>> value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchParameter parameter = (SearchParameter) o;
        return Objects.equals(name, parameter.name) && Objects.equals(value, parameter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "SearchParameter{" +
                "name=" + name +
                ", value=" + value +
                '}';
    }
}
