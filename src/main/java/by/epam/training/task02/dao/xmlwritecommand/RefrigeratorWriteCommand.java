package by.epam.training.task02.dao.xmlwritecommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.Refrigerator;
import org.jdom2.Element;

/**
 * Implementation of {@code ApplianceWriteCommand}
 * Suited for use with instances of {@code Refrigerator}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see Refrigerator
 */
class RefrigeratorWriteCommand implements ApplianceWriteCommand {

    @Override
    public Element makeElement(Appliance appliance) {
        if (!(appliance.getClass().equals(Refrigerator.class))) {
            return null;
        }

        Refrigerator fridge = (Refrigerator) appliance;

        Element element = new Element("Refrigerator");
        WriteCommandUtil.setGeneralParameters(element, fridge);
        element.addContent(new Element("POWER_CONSUMPTION").setText(String.valueOf(fridge.getPowerConsumption())));
        element.addContent(new Element("FREEZER_CAPACITY").setText(String.valueOf(fridge.getFreezerCapacityInLiters())));
        element.addContent(new Element("OVERALL_CAPACITY").setText(String.valueOf(fridge.getOverallCapacityInLiters())));

        return element;
    }
}
