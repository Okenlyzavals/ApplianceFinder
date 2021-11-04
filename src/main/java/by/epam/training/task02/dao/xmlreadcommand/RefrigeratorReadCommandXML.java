package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.Refrigerator;
import by.epam.training.task02.entity.criteria.Criteria;
import by.epam.training.task02.entity.criteria.SearchCriteria;
import by.epam.training.task02.entity.criteria.SearchParameter;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@code ApplianceReadCommandXML}
 * Suited for use with instances of {@code Refrigerator}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see by.epam.training.task02.entity.Refrigerator
 */
class RefrigeratorReadCommandXML implements ApplianceReadCommandXML {

    @Override
    public List<Appliance> getAppliances(Criteria criteria) {

        List<Element> catalogue = XMLReadCommandUtil.getElementsInCatalogue("Refrigerator_catalogue");
        List<Appliance> result = new ArrayList<>();

        if (catalogue == null) {
            return result;
        }

        for (Element refrigeratorElement : catalogue) {
            Refrigerator refrigeratorToCheck = new Refrigerator();

            XMLReadCommandUtil.setGeneralApplianceParametersFromElement(refrigeratorToCheck, refrigeratorElement);
            refrigeratorToCheck.setPowerConsumption(Integer.parseInt(refrigeratorElement.getChildText("POWER_CONSUMPTION")));

            refrigeratorToCheck.setOverallCapacityInLiters(Double.parseDouble(refrigeratorElement.getChildText("OVERALL_CAPACITY")));
            refrigeratorToCheck.setFreezerCapacityInLiters(Double.parseDouble(refrigeratorElement.getChildText("FREEZER_CAPACITY")));

            if (satisfiesCriteria(criteria, refrigeratorToCheck)) {
                result.add(refrigeratorToCheck);
            }
        }

        return result;
    }

    /**
     * Checks if {@code Refrigerator} object satisfies the criteria.
     *
     * @param criteria     The search criteria.
     * @param refrigerator The Refrigerator instance to be checked.
     * @return {@code true} if Refrigerator object satisfies the criteria,
     * {@code false} otherwise.
     */
    private boolean satisfiesCriteria(Criteria criteria, Refrigerator refrigerator) {
        boolean isSatisfactory = XMLReadCommandUtil.checkGeneralApplianceParameters(refrigerator, criteria);

        if (!isSatisfactory) {
            return false;
        }

        for (SearchParameter parameter : criteria.getParameters()) {
            SearchCriteria.CriteriaParameterName name = parameter.getName();

            if (name.equals(SearchCriteria.WiredAppliance.POWER_CONSUMPTION)) {
                isSatisfactory = parameter.getValue().isSatisfactory(refrigerator.getPowerConsumption());
            } else if (name.equals(SearchCriteria.Refrigerator.FREEZER_CAPACITY)) {
                isSatisfactory = parameter.getValue().isSatisfactory(refrigerator.getFreezerCapacityInLiters());
            } else if (name.equals(SearchCriteria.Refrigerator.OVERALL_CAPACITY)) {
                isSatisfactory = parameter.getValue().isSatisfactory(refrigerator.getOverallCapacityInLiters());
            }

            if (!isSatisfactory) {
                break;
            }
        }

        return isSatisfactory;
    }
}
