package by.epam.training.task02.dao.xmlwritecommand;

import by.epam.training.task02.entity.Appliance;
import org.jdom2.Element;

/**
 * Utility class for writing commands.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see ApplianceWriteCommand
 */
class WriteCommandUtil {

    /**
     * Adds general parameters of any appliance to an XML element.
     *
     * @param element   Element to modify.
     * @param appliance Appliance to get parameters from.
     * @see Appliance
     */
    static void setGeneralParameters(Element element, Appliance appliance) {
        if (element == null || appliance == null) {
            return;
        }

        element.addContent(new Element("WEIGHT").setText(String.valueOf(appliance.getWeight())));
        element.addContent(new Element("WIDTH").setText(String.valueOf(appliance.getWidth())));
        element.addContent(new Element("HEIGHT").setText(String.valueOf(appliance.getHeight())));
        element.addContent(new Element("DEPTH").setText(String.valueOf(appliance.getDepth())));
    }

    /**
     * This class is not supposed to be instantiated.
     */
    private WriteCommandUtil() {
    }

}
