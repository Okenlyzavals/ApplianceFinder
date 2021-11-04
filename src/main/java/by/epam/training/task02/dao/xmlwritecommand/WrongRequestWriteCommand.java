package by.epam.training.task02.dao.xmlwritecommand;

import by.epam.training.task02.entity.Appliance;
import org.jdom2.Element;

/**
 * Implementation of {@code ApplianceWriteCommand}
 * Used to indicate incorrect requests.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see WriteCommandProvider
 */
class WrongRequestWriteCommand implements ApplianceWriteCommand {
    @Override
    public Element makeElement(Appliance appliance) {
        return null;
    }
}
