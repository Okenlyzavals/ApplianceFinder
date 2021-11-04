package by.epam.training.task02.dao.xmlwritecommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.Oven;
import org.jdom2.Element;

/**
 * Implementation of {@code ApplianceWriteCommand}
 * Suited for use with instances of {@code Oven}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see Oven
 */
class OvenWriteCommand implements ApplianceWriteCommand {
    @Override
    public Element makeElement(Appliance appliance) {

        if (!(appliance.getClass().equals(Oven.class))) {
            return null;
        }

        Oven oven = (Oven) appliance;

        Element element = new Element("Oven");
        WriteCommandUtil.setGeneralParameters(element, oven);
        element.addContent(new Element("POWER_CONSUMPTION").setText(String.valueOf(oven.getPowerConsumption())));
        element.addContent(new Element("CAPACITY").setText(String.valueOf(oven.getCapacityInLiters())));

        return element;
    }
}
