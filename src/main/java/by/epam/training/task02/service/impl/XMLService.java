package by.epam.training.task02.service.impl;

import by.epam.training.task02.dao.ApplianceDAO;
import by.epam.training.task02.dao.DAOFactory;
import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.criteria.Criteria;
import by.epam.training.task02.service.ApplianceService;
import by.epam.training.task02.service.validation.Validator;

import java.util.List;

/**
 * Implementation of {@code ApplianceService} suited for working with XML files.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class XMLService implements ApplianceService {

    @Override
    public List<Appliance> find(Criteria criteria) {

        if (!Validator.validate(criteria)) {
            return null;
        }

        DAOFactory factory = DAOFactory.getInstance();
        ApplianceDAO applianceDAO = factory.getApplianceDAO();

        return applianceDAO.find(criteria);
    }

    @Override
    public boolean write(List<Appliance> appliances) {

        if (appliances == null || appliances.isEmpty()) {
            return false;
        }

        DAOFactory factory = DAOFactory.getInstance();
        ApplianceDAO applianceDAO = factory.getApplianceDAO();

        return applianceDAO.write(appliances);
    }

}
