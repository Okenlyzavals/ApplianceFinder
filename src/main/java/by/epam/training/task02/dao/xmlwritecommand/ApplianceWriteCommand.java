package by.epam.training.task02.dao.xmlwritecommand;

import by.epam.training.task02.entity.Appliance;
import org.jdom2.Element;

/**
 * Interface for a command that is used to make
 * XML elements out of Appliance objects
 * So they can be outputted into storage file.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see Appliance
 * @see WriteCommandProvider
 */
public interface ApplianceWriteCommand {

    /**
     * Makes a new XML element out of Appliance object
     *
     * @param appliance Appliance object to make element out of.
     * @return new instance of {@code org.jdom2.Element};
     * null if type of Appliance object does not match
     * specific implementation of this interface
     * or if write request is incorrect.
     */
    Element makeElement(Appliance appliance);

}
