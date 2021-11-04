package by.epam.training.task02.dao.xmlwritecommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.TabletPC;
import org.jdom2.Element;

/**
 * Implementation of {@code ApplianceWriteCommand}
 * Suited for use with instances of {@code TabletPC}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see TabletPC
 */
class TabletWriteCommand implements ApplianceWriteCommand {

    @Override
    public Element makeElement(Appliance appliance) {
        if (!(appliance.getClass().equals(TabletPC.class))) {
            return null;
        }

        TabletPC tablet = (TabletPC) appliance;

        Element element = new Element("TabletPC");
        WriteCommandUtil.setGeneralParameters(element, tablet);
        element.addContent(new Element("DISPLAY_INCHES").setText(String.valueOf(tablet.getDisplayInches())));
        element.addContent(new Element("BATTERY_CAPACITY").setText(String.valueOf(tablet.getBatteryCapacity())));
        element.addContent(new Element("MEMORY_ROM").setText(String.valueOf(tablet.getMemoryRom())));
        element.addContent(new Element("FLASH_MEMORY_CAPACITY").setText(String.valueOf(tablet.getFlashMemoryCapacity())));
        element.addContent(new Element("COLOR").setText(tablet.getColor()));

        return element;
    }

}
