package by.epam.training.task02.dao.xmlwritecommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.VacuumCleaner;
import org.jdom2.Element;

/**
 * Implementation of {@code ApplianceWriteCommand}
 * Suited for use with instances of {@code VacuumCleaner}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see VacuumCleaner
 */
class VacuumCleanerWriteCommand implements ApplianceWriteCommand {
    @Override
    public Element makeElement(Appliance appliance) {
        if (!(appliance.getClass().equals(VacuumCleaner.class))) {
            return null;
        }

        VacuumCleaner cleaner = (VacuumCleaner) appliance;

        Element element = new Element("VacuumCleaner");
        WriteCommandUtil.setGeneralParameters(element, cleaner);
        element.addContent(new Element("POWER_CONSUMPTION").setText(String.valueOf(cleaner.getPowerConsumption())));
        element.addContent(new Element("FILTER_TYPE").setText(cleaner.getFilterType()));
        element.addContent(new Element("BAG_TYPE").setText(cleaner.getBagType()));
        element.addContent(new Element("WAND_TYPE").setText(cleaner.getWandType()));
        element.addContent(new Element("MOTOR_SPEED_REGULATION").setText(String.valueOf(cleaner.getMotorSpeedRegulation())));
        element.addContent(new Element("CLEANING_WIDTH").setText(String.valueOf(cleaner.getCleaningWidth())));

        return element;
    }
}
