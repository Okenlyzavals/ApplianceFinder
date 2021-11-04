package by.epam.training.task02.dao.xmlwritecommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.Laptop;
import org.jdom2.Element;

/**
 * Implementation of {@code ApplianceWriteCommand}
 * Suited for use with instances of {@code Laptop}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see Laptop
 */
class LaptopWriteCommand implements ApplianceWriteCommand {

    @Override
    public Element makeElement(Appliance appliance) {
        if (!(appliance.getClass().equals(Laptop.class))) {
            return null;
        }

        Laptop laptop = (Laptop) appliance;

        Element element = new Element("Laptop");
        WriteCommandUtil.setGeneralParameters(element, laptop);
        element.addContent(new Element("DISPLAY_INCHES").setText(String.valueOf(laptop.getDisplayInches())));
        element.addContent(new Element("BATTERY_CAPACITY").setText(String.valueOf(laptop.getBatteryCapacity())));
        element.addContent(new Element("OS").setText(laptop.getOperationalSystem()));
        element.addContent(new Element("MEMORY_ROM").setText(String.valueOf(laptop.getMemoryRom())));
        element.addContent(new Element("SYSTEM_MEMORY").setText(String.valueOf(laptop.getSystemMemory())));
        element.addContent(new Element("CPU").setText(String.valueOf(laptop.getCpu())));

        return element;
    }

}
