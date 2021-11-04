package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.dao.Repository;
import by.epam.training.task02.dao.xmlwritecommand.ApplianceWriteCommand;
import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.criteria.Criteria;
import by.epam.training.task02.entity.criteria.SearchCriteria;
import by.epam.training.task02.entity.criteria.SearchParameter;
import org.jdom2.Element;

import java.util.List;

/**
 * Utility class for reading commands.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see ApplianceWriteCommand
 */
final class XMLReadCommandUtil {

    /**
     * Modifies general parameters (inherited by all appliances)
     * in given Appliance. Gets values from {@code org.jdom2.Element}
     *
     * @param appliance Appliance to modify.
     * @param element   Element to get parameters from.
     */
    static void setGeneralApplianceParametersFromElement(Appliance appliance, Element element) {
        appliance.setWeight(Double.parseDouble(element.getChildText("WEIGHT")));
        appliance.setHeight(Double.parseDouble(element.getChildText("HEIGHT")));
        appliance.setDepth(Double.parseDouble(element.getChildText("DEPTH")));
        appliance.setWidth(Double.parseDouble(element.getChildText("WIDTH")));
    }

    /**
     * Checks if given Appliance object satisfies the requirements of a criteria.
     *
     * @param appliance Appliance to check.
     * @param criteria  The search criteria.
     * @return {@code true} if parameters of appliance satisfy the criteria,
     * {@code false} otherwise.
     */
    static boolean checkGeneralApplianceParameters(Appliance appliance, Criteria criteria) {
        boolean isSatisfactory = true;

        for (SearchParameter parameter : criteria.getParameters()) {
            SearchCriteria.CriteriaParameterName name = parameter.getName();

            if (name.equals(SearchCriteria.AnyAppliance.WEIGHT)) {
                isSatisfactory = parameter.getValue().isSatisfactory(appliance.getWeight());
            }
            if (name.equals(SearchCriteria.AnyAppliance.HEIGHT)) {
                isSatisfactory = parameter.getValue().isSatisfactory(appliance.getHeight());
            }
            if (name.equals(SearchCriteria.AnyAppliance.DEPTH)) {
                isSatisfactory = parameter.getValue().isSatisfactory(appliance.getDepth());
            }
            if (name.equals(SearchCriteria.AnyAppliance.WIDTH)) {
                isSatisfactory = parameter.getValue().isSatisfactory(appliance.getWidth());
            }
        }

        return isSatisfactory;
    }

    /**
     * Retrieves child elements from org.jdom2.Element
     *
     * @param catalogueName Name of the element.
     * @return List of child elements of Element with name that equals catalogueName.
     * List will be empty if there are no such elements.
     */
    static List<Element> getElementsInCatalogue(String catalogueName) {
        Repository repository = Repository.getInstance();
        List<Element> catalogues = repository.getElements();

        List<Element> result = null;
        for (Element catalogue : catalogues) {
            if (catalogue.getName().equals(catalogueName)) {
                result = catalogue.getChildren();
            }
        }

        return result;
    }

}
