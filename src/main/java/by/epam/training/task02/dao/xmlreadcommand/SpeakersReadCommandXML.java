package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.Speakers;
import by.epam.training.task02.entity.criteria.Criteria;
import by.epam.training.task02.entity.criteria.SearchCriteria;
import by.epam.training.task02.entity.criteria.SearchParameter;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@code ApplianceReadCommandXML}
 * Suited for use with instances of {@code Speakers}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see by.epam.training.task02.entity.Speakers
 */
class SpeakersReadCommandXML implements ApplianceReadCommandXML {

    @Override
    public List<Appliance> getAppliances(Criteria criteria) {
        List<Element> catalogue = XMLReadCommandUtil.getElementsInCatalogue("Speakers_catalogue");
        List<Appliance> result = new ArrayList<>();

        if (catalogue == null) {
            return result;
        }

        for (Element speakersElement : catalogue) {
            Speakers speakersToCheck = new Speakers();

            XMLReadCommandUtil.setGeneralApplianceParametersFromElement(speakersToCheck, speakersElement);
            speakersToCheck.setPowerConsumption(Integer.parseInt(speakersElement.getChildText("POWER_CONSUMPTION")));
            speakersToCheck.setNumberOfSpeakers(Integer.parseInt(speakersElement.getChildText("NUMBER_OF_SPEAKERS")));
            speakersToCheck.setCordLengthInMeters(Double.parseDouble(speakersElement.getChildText("CORD_LENGTH")));
            speakersToCheck.setMaximalFrequency(Double.parseDouble(speakersElement.getChildText("MAXIMAL_FREQUENCY")));
            speakersToCheck.setMinimalFrequency(Double.parseDouble(speakersElement.getChildText("MINIMAL_FREQUENCY")));


            if (satisfiesCriteria(criteria, speakersToCheck)) {
                result.add(speakersToCheck);
            }
        }

        return result;
    }

    /**
     * Checks if {@code Speakers} object satisfies the criteria.
     *
     * @param criteria The search criteria.
     * @param speakers The Speakers instance to be checked.
     * @return {@code true} if Speakers object satisfies the criteria,
     * {@code false} otherwise.
     */
    private boolean satisfiesCriteria(Criteria criteria, Speakers speakers) {
        boolean isSatisfactory = XMLReadCommandUtil.checkGeneralApplianceParameters(speakers, criteria);

        if (!isSatisfactory) {
            return false;
        }

        for (SearchParameter parameter : criteria.getParameters()) {
            SearchCriteria.CriteriaParameterName name = parameter.getName();

            if (name.equals(SearchCriteria.WiredAppliance.POWER_CONSUMPTION)) {
                isSatisfactory = parameter.getValue().isSatisfactory(speakers.getPowerConsumption());
            } else if (name.equals(SearchCriteria.Speakers.NUMBER_OF_SPEAKERS)) {
                isSatisfactory = parameter.getValue().isSatisfactory(speakers.getNumberOfSpeakers());
            } else if (name.equals(SearchCriteria.Speakers.CORD_LENGTH)) {
                isSatisfactory = parameter.getValue().isSatisfactory(speakers.getCordLengthInMeters());
            } else if (name.equals(SearchCriteria.Speakers.MAXIMAL_FREQUENCY)) {
                isSatisfactory = parameter.getValue().isSatisfactory(speakers.getMaximalFrequency());
            } else if (name.equals(SearchCriteria.Speakers.MINIMAL_FREQUENCY)) {
                isSatisfactory = parameter.getValue().isSatisfactory(speakers.getMinimalFrequency());
            }

            if (!isSatisfactory) {
                break;
            }
        }

        return isSatisfactory;
    }

}
