package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.WiredAppliance;
import by.epam.training.task02.entity.criteria.Criteria;

import java.util.List;

/**
 * Implementation of {@code ApplianceReadCommandXML}
 * Suited for use with instances of {@code BatteryPoweredAppliance}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see by.epam.training.task02.entity.BatteryPoweredAppliance
 * @see AnyApplianceReadCommandXML
 */
class BatteryPoweredApplianceReadCommandXML extends AnyApplianceReadCommandXML {

    @Override
    public List<Appliance> getAppliances(Criteria criteria) {
        List<Appliance> result = super.getAppliances(criteria);
        result.removeIf(e -> (e instanceof WiredAppliance));

        return result;
    }

}
