package by.epam.training.task02.service;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.criteria.Criteria;

import java.util.List;

/**
 * Provides a general interface for service classes.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public interface ApplianceService {

    /**
     * Returns a list of appliances that satisfy the criteria parameter.
     * <p> Gets data from DAL. The list will be empty if no appliances were found.
     *
     * @param criteria The search criteria
     * @return List of Appliance objects satisfying the criteria;
     * {@code null} if criteria is invalid.
     */
    List<Appliance> find(Criteria criteria);

    /**
     * Writes the list of appliances to the data file via DAL.
     *
     * @param appliances List of appliances to be written into data.
     * @return {@code true} if the operation went successfully;
     * {@code false} if the list is empty or if the operation failed.
     */
    boolean write(List<Appliance> appliances);


}
