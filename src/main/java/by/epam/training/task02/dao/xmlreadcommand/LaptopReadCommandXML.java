package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.Laptop;
import by.epam.training.task02.entity.criteria.Criteria;
import by.epam.training.task02.entity.criteria.SearchCriteria;
import by.epam.training.task02.entity.criteria.SearchParameter;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@code ApplianceReadCommandXML}
 * Suited for use with instances of {@code Laptop}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see by.epam.training.task02.entity.Laptop
 */
class LaptopReadCommandXML implements ApplianceReadCommandXML {

    @Override
    public List<Appliance> getAppliances(Criteria criteria) {

        List<Element> catalogue = XMLReadCommandUtil.getElementsInCatalogue("Laptop_catalogue");
        List<Appliance> result = new ArrayList<>();

        if (catalogue == null) {
            return result;
        }

        for (Element laptopElement : catalogue) {
            Laptop laptopToCheck = new Laptop();

            XMLReadCommandUtil.setGeneralApplianceParametersFromElement(laptopToCheck, laptopElement);
            laptopToCheck.setBatteryCapacity(Double.parseDouble(laptopElement.getChildText("BATTERY_CAPACITY")));
            laptopToCheck.setCpu(Double.parseDouble(laptopElement.getChildText("CPU")));
            laptopToCheck.setDisplayInches(Double.parseDouble(laptopElement.getChildText("DISPLAY_INCHES")));
            laptopToCheck.setMemoryRom(Integer.parseInt(laptopElement.getChildText("MEMORY_ROM")));
            laptopToCheck.setOperationalSystem(laptopElement.getChildText("OS"));
            laptopToCheck.setSystemMemory(Integer.parseInt(laptopElement.getChildText("SYSTEM_MEMORY")));

            if (satisfiesCriteria(criteria, laptopToCheck)) {
                result.add(laptopToCheck);
            }
        }

        return result;
    }

    /**
     * Checks if {@code Laptop} object satisfies the criteria.
     *
     * @param criteria The search criteria.
     * @param laptop   The Laptop instance to be checked.
     * @return {@code true} if Laptop object satisfies the criteria,
     * {@code false} otherwise.
     */
    private boolean satisfiesCriteria(Criteria criteria, Laptop laptop) {
        boolean isSatisfactory = XMLReadCommandUtil.checkGeneralApplianceParameters(laptop, criteria);

        if (!isSatisfactory) {
            return false;
        }

        for (SearchParameter parameter : criteria.getParameters()) {
            SearchCriteria.CriteriaParameterName name = parameter.getName();

            if (name.equals(SearchCriteria.BatteryPoweredAppliance.BATTERY_CAPACITY)) {
                isSatisfactory = parameter.getValue().isSatisfactory(laptop.getBatteryCapacity());
            } else if (name.equals(SearchCriteria.Laptop.CPU)) {
                isSatisfactory = parameter.getValue().isSatisfactory(laptop.getCpu());
            } else if (name.equals(SearchCriteria.Laptop.DISPLAY_INCHES)) {
                isSatisfactory = parameter.getValue().isSatisfactory(laptop.getDisplayInches());
            } else if (name.equals(SearchCriteria.Laptop.MEMORY_ROM)) {
                isSatisfactory = parameter.getValue().isSatisfactory(laptop.getMemoryRom());
            } else if (name.equals(SearchCriteria.Laptop.SYSTEM_MEMORY)) {
                isSatisfactory = parameter.getValue().isSatisfactory(laptop.getSystemMemory());
            } else if (name.equals(SearchCriteria.Laptop.OS)) {
                isSatisfactory = parameter.getValue().isSatisfactory(laptop.getOperationalSystem());
            }

            if (!isSatisfactory) {
                break;
            }
        }

        return isSatisfactory;
    }
}
