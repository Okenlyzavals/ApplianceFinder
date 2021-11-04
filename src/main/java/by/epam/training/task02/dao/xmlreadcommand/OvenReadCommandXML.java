package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.Oven;
import by.epam.training.task02.entity.criteria.Criteria;
import by.epam.training.task02.entity.criteria.SearchCriteria;
import by.epam.training.task02.entity.criteria.SearchParameter;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@code ApplianceReadCommandXML}
 * Suited for use with instances of {@code Oven}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see by.epam.training.task02.entity.Oven
 */
class OvenReadCommandXML implements ApplianceReadCommandXML {

    @Override
    public List<Appliance> getAppliances(Criteria criteria) {

        List<Element> catalogue = XMLReadCommandUtil.getElementsInCatalogue("Oven_catalogue");
        List<Appliance> result = new ArrayList<>();

        if (catalogue == null) {
            return result;
        }

        for (Element ovenElement : catalogue) {
            Oven ovenToCheck = new Oven();

            XMLReadCommandUtil.setGeneralApplianceParametersFromElement(ovenToCheck, ovenElement);
            ovenToCheck.setPowerConsumption(Integer.parseInt(ovenElement.getChildText("POWER_CONSUMPTION")));
            ovenToCheck.setCapacityInLiters(Double.parseDouble(ovenElement.getChildText("CAPACITY")));

            if (satisfiesCriteria(criteria, ovenToCheck)) {
                result.add(ovenToCheck);
            }
        }

        return result;
    }

    /**
     * Checks if {@code Oven} object satisfies the criteria.
     *
     * @param criteria The search criteria.
     * @param oven     The Oven instance to be checked.
     * @return {@code true} if Oven object satisfies the criteria,
     * {@code false} otherwise.
     */
    private boolean satisfiesCriteria(Criteria criteria, Oven oven) {
        boolean isSatisfactory = XMLReadCommandUtil.checkGeneralApplianceParameters(oven, criteria);

        if (!isSatisfactory) {
            return false;
        }

        for (SearchParameter parameter : criteria.getParameters()) {
            SearchCriteria.CriteriaParameterName name = parameter.getName();

            if (name.equals(SearchCriteria.WiredAppliance.POWER_CONSUMPTION)) {
                isSatisfactory = parameter.getValue().isSatisfactory(oven.getPowerConsumption());
            } else if (name.equals(SearchCriteria.Oven.CAPACITY)) {
                isSatisfactory = parameter.getValue().isSatisfactory(oven.getCapacityInLiters());
            }

            if (!isSatisfactory) {
                break;
            }
        }

        return isSatisfactory;
    }

}
