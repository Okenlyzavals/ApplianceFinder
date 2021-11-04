package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.BatteryPoweredAppliance;
import by.epam.training.task02.entity.criteria.Criteria;

import java.util.List;

/**
 * Implementation of {@code ApplianceReadCommandXML}
 * Suited for use with instances of {@code WiredAppliance}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see by.epam.training.task02.entity.WiredAppliance
 * @see AnyApplianceReadCommandXML
 */
class WiredApplianceReadCommandXML extends AnyApplianceReadCommandXML {
    @Override
    public List<Appliance> getAppliances(Criteria criteria) {
        List<Appliance> result = super.getAppliances(criteria);
        result.removeIf(e -> (e instanceof BatteryPoweredAppliance));

        return result;
    }
}
