package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.dao.xmlwritecommand.WriteCommandProvider;
import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.criteria.Criteria;

import java.util.List;

/**
 * Interface for a command that is used to retrieve
 * specified Appliance objects from XML Document.
 * Uses repository to access Document.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see Appliance
 * @see by.epam.training.task02.dao.Repository
 * @see WriteCommandProvider
 */
public interface ApplianceReadCommandXML {

    /**
     * Retrieves appliances from storage file by criteria.
     *
     * @param criteria The search criteria.
     * @return List of Appliance objects satisfying the criteria.
     */
    List<Appliance> getAppliances(Criteria criteria);
}
