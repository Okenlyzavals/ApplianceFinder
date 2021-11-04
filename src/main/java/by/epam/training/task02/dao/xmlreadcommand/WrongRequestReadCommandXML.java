package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.criteria.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@code ApplianceReadCommandXML}
 * Used to indicate incorrect requests.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see XMLReadCommandProvider
 */
class WrongRequestReadCommandXML implements ApplianceReadCommandXML {

    @Override
    public List<Appliance> getAppliances(Criteria criteria) {
        return new ArrayList<>();
    }

}
