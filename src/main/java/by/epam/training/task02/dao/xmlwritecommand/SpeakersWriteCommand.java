package by.epam.training.task02.dao.xmlwritecommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.Speakers;
import org.jdom2.Element;


/**
 * Implementation of {@code ApplianceWriteCommand}
 * Suited for use with instances of {@code Speakers}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see Speakers
 */
class SpeakersWriteCommand implements ApplianceWriteCommand {
    @Override
    public Element makeElement(Appliance appliance) {
        if (!(appliance.getClass().equals(Speakers.class))) {
            return null;
        }

        Speakers speakers = (Speakers) appliance;

        Element element = new Element("Speakers");
        WriteCommandUtil.setGeneralParameters(element, speakers);
        element.addContent(new Element("POWER_CONSUMPTION").setText(String.valueOf(speakers.getPowerConsumption())));
        element.addContent(new Element("NUMBER_OF_SPEAKERS").setText(String.valueOf(speakers.getNumberOfSpeakers())));
        element.addContent(new Element("MINIMAL_FREQUENCY").setText(String.valueOf(speakers.getMinimalFrequency())));
        element.addContent(new Element("MAXIMAL_FREQUENCY").setText(String.valueOf(speakers.getMaximalFrequency())));
        element.addContent(new Element("CORD_LENGTH").setText(String.valueOf(speakers.getCordLengthInMeters())));

        return element;
    }
}
