package by.epam.training.task02.dao;

import by.epam.training.task02.dao.impl.XMLApplianceDAO;

/**
 * Factory class for DAOs.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class DAOFactory {

    /**
     * Current instance of {@code DAOFactory}
     */
    private static final DAOFactory instance = new DAOFactory();
    /**
     * Instance of {@code ApplianceDAO}, currently references {@code XMLApplianceDAO}
     *
     * @see XMLApplianceDAO
     */
    private final ApplianceDAO applianceDAO = new XMLApplianceDAO();

    /**
     * This is a singleton, thus it is must not be instantiated.
     */
    private DAOFactory() {
    }

    /**
     * Returns the current instance of this factory.
     *
     * @return An instance of {@code DAOFactory}.
     */
    public static DAOFactory getInstance() {
        return instance;
    }

    /**
     * @return An instance of {@code ApplianceDAO}
     * @see ApplianceDAO
     */
    public ApplianceDAO getApplianceDAO() {
        return applianceDAO;
    }
}
