package by.epam.training.task02.service;

import by.epam.training.task02.service.impl.XMLService;

/**
 * Factory class for Service objects.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class ServiceFactory {

    /**
     * Current instance of {@code ServiceFactory}
     */
    private static final ServiceFactory instance = new ServiceFactory();

    /**
     * Instance of {@code ApplianceService}, currently references {@code XMLService}
     *
     * @see XMLService
     */
    private final ApplianceService applianceService = new XMLService();

    /**
     * This is a singleton, thus it is must not be instantiated.
     */
    private ServiceFactory() {
    }

    /**
     * Returns the current instance of this factory.
     *
     * @return An instance of {@code ServiceFactory}.
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * @return An instance of {@code ApplianceService}
     * @see ApplianceService
     */
    public ApplianceService getApplianceService() {
        return applianceService;
    }
}
