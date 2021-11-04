package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.VacuumCleaner;
import by.epam.training.task02.entity.criteria.Criteria;
import by.epam.training.task02.entity.criteria.SearchCriteria;
import by.epam.training.task02.entity.criteria.SearchParameter;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@code ApplianceReadCommandXML}
 * Suited for use with instances of {@code VacuumCleaner}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see by.epam.training.task02.entity.VacuumCleaner
 */
class VacuumCleanerReadCommandXML implements ApplianceReadCommandXML {

    @Override
    public List<Appliance> getAppliances(Criteria criteria) {
        List<Element> catalogue = XMLReadCommandUtil.getElementsInCatalogue("VacuumCleaner_catalogue");
        List<Appliance> result = new ArrayList<>();

        if (catalogue == null) {
            return result;
        }

        for (Element cleanerElement : catalogue) {
            VacuumCleaner cleanerToCheck = new VacuumCleaner();

            XMLReadCommandUtil.setGeneralApplianceParametersFromElement(cleanerToCheck, cleanerElement);
            cleanerToCheck.setPowerConsumption(Integer.parseInt(cleanerElement.getChildText("POWER_CONSUMPTION")));
            cleanerToCheck.setCleaningWidth(Double.parseDouble(cleanerElement.getChildText("CLEANING_WIDTH")));
            cleanerToCheck.setBagType(cleanerElement.getChildText("BAG_TYPE"));
            cleanerToCheck.setFilterType(cleanerElement.getChildText("FILTER_TYPE"));
            cleanerToCheck.setWandType(cleanerElement.getChildText("WAND_TYPE"));
            cleanerToCheck.setMotorSpeedRegulation(Integer.parseInt(cleanerElement.getChildText("MOTOR_SPEED_REGULATION")));

            if (satisfiesCriteria(criteria, cleanerToCheck)) {
                result.add(cleanerToCheck);
            }
        }

        return result;
    }

    /**
     * Checks if {@code TabletPC} object satisfies the criteria.
     *
     * @param criteria      The search criteria.
     * @param vacuumCleaner The VacuumCleaner instance to be checked.
     * @return {@code true} if VacuumCleaner object satisfies the criteria,
     * {@code false} otherwise.
     */
    private boolean satisfiesCriteria(Criteria criteria, VacuumCleaner vacuumCleaner) {
        boolean isSatisfactory = XMLReadCommandUtil.checkGeneralApplianceParameters(vacuumCleaner, criteria);

        if (!isSatisfactory) {
            return false;
        }

        for (SearchParameter parameter : criteria.getParameters()) {
            SearchCriteria.CriteriaParameterName name = parameter.getName();

            if (name.equals(SearchCriteria.WiredAppliance.POWER_CONSUMPTION)) {
                isSatisfactory = parameter.getValue().isSatisfactory(vacuumCleaner.getPowerConsumption());
            } else if (name.equals(SearchCriteria.VacuumCleaner.FILTER_TYPE)) {
                isSatisfactory = parameter.getValue().isSatisfactory(vacuumCleaner.getFilterType());
            } else if (name.equals(SearchCriteria.VacuumCleaner.BAG_TYPE)) {
                isSatisfactory = parameter.getValue().isSatisfactory(vacuumCleaner.getBagType());
            } else if (name.equals(SearchCriteria.VacuumCleaner.WAND_TYPE)) {
                isSatisfactory = parameter.getValue().isSatisfactory(vacuumCleaner.getWandType());
            } else if (name.equals(SearchCriteria.VacuumCleaner.MOTOR_SPEED_REGULATION)) {
                isSatisfactory = parameter.getValue().isSatisfactory(vacuumCleaner.getMotorSpeedRegulation());
            } else if (name.equals(SearchCriteria.VacuumCleaner.CLEANING_WIDTH)) {
                isSatisfactory = parameter.getValue().isSatisfactory(vacuumCleaner.getCleaningWidth());
            }

            if (!isSatisfactory) {
                break;
            }
        }

        return isSatisfactory;
    }
}
