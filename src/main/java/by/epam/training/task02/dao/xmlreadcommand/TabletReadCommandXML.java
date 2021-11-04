package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.TabletPC;
import by.epam.training.task02.entity.criteria.Criteria;
import by.epam.training.task02.entity.criteria.SearchCriteria;
import by.epam.training.task02.entity.criteria.SearchParameter;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@code ApplianceReadCommandXML}
 * Suited for use with instances of {@code TabletPC}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see by.epam.training.task02.entity.TabletPC
 */
class TabletReadCommandXML implements ApplianceReadCommandXML {

    @Override
    public List<Appliance> getAppliances(Criteria criteria) {
        List<Element> catalogue = XMLReadCommandUtil.getElementsInCatalogue("TabletPC_catalogue");
        List<Appliance> result = new ArrayList<>();

        if (catalogue == null) {
            return result;
        }

        for (Element tabletElement : catalogue) {
            TabletPC tabletToCheck = new TabletPC();

            XMLReadCommandUtil.setGeneralApplianceParametersFromElement(tabletToCheck, tabletElement);
            tabletToCheck.setBatteryCapacity(Double.parseDouble(tabletElement.getChildText("BATTERY_CAPACITY")));
            tabletToCheck.setDisplayInches(Double.parseDouble(tabletElement.getChildText("DISPLAY_INCHES")));
            tabletToCheck.setColor(tabletElement.getChildText("COLOR"));
            tabletToCheck.setFlashMemoryCapacity(Double.parseDouble(tabletElement.getChildText("FLASH_MEMORY_CAPACITY")));
            tabletToCheck.setMemoryRom(Integer.parseInt(tabletElement.getChildText("MEMORY_ROM")));

            if (satisfiesCriteria(criteria, tabletToCheck)) {
                result.add(tabletToCheck);
            }
        }

        return result;

    }

    /**
     * Checks if {@code TabletPC} object satisfies the criteria.
     *
     * @param criteria The search criteria.
     * @param tabletPC The TabletPC instance to be checked.
     * @return {@code true} if TabletPC object satisfies the criteria,
     * {@code false} otherwise.
     */
    private boolean satisfiesCriteria(Criteria criteria, TabletPC tabletPC) {
        boolean isSatisfactory = XMLReadCommandUtil.checkGeneralApplianceParameters(tabletPC, criteria);

        if (!isSatisfactory) {
            return false;
        }

        for (SearchParameter parameter : criteria.getParameters()) {
            SearchCriteria.CriteriaParameterName name = parameter.getName();

            if (name.equals(SearchCriteria.BatteryPoweredAppliance.BATTERY_CAPACITY)) {
                isSatisfactory = parameter.getValue().isSatisfactory(tabletPC.getBatteryCapacity());
            } else if (name.equals(SearchCriteria.TabletPC.DISPLAY_INCHES)) {
                isSatisfactory = parameter.getValue().isSatisfactory(tabletPC.getDisplayInches());
            } else if (name.equals(SearchCriteria.TabletPC.MEMORY_ROM)) {
                isSatisfactory = parameter.getValue().isSatisfactory(tabletPC.getMemoryRom());
            } else if (name.equals(SearchCriteria.TabletPC.COLOR)) {
                isSatisfactory = parameter.getValue().isSatisfactory(tabletPC.getColor());
            } else if (name.equals(SearchCriteria.TabletPC.FLASH_MEMORY_CAPACITY)) {
                isSatisfactory = parameter.getValue().isSatisfactory(tabletPC.getFlashMemoryCapacity());
            }

            if (!isSatisfactory) {
                break;
            }

        }

        return isSatisfactory;
    }

}
