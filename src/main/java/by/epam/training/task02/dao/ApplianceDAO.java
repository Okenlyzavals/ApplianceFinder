package by.epam.training.task02.dao;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.criteria.Criteria;

import java.util.List;

/**
 * Interface for DAOs for appliances storage.
 * <p>Allows to search for appliances via criteria
 * and to save them into storage.
 * The storage file can be located within {@code Repository}.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see Repository
 */
public interface ApplianceDAO {

    /**
     * Returns a list of appliances that satisfy the criteria parameter.
     * The list will be empty if no appliances were found.
     *
     * @param criteria The search criteria.
     * @return List of Appliance objects satisfying the criteria.
     */
    List<Appliance> find(Criteria criteria);

    /**
     * Writes the list of appliances to the storage file.
     *
     * @param appliances List of appliances to be written into storage.
     * @return {@code true} if the operation went successfully;
     * {@code false} otherwise.
     */
    boolean write(List<Appliance> appliances);


}
