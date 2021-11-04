package by.epam.training.task02.dao.xmlreadcommand;

import by.epam.training.task02.dao.Repository;
import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.criteria.Criteria;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@code ApplianceReadCommandXML}
 * Suited for use with instances of {@code Appliance}
 *
 * @author Baranovsky E. K.
 * @version 1.0
 * @see Appliance
 * @see XMLReadCommandProvider
 */
class AnyApplianceReadCommandXML implements ApplianceReadCommandXML {

    @Override
    public List<Appliance> getAppliances(Criteria criteria) {
        Repository repository = Repository.getInstance();
        List<Element> catalogues = repository.getElements();
        XMLReadCommandProvider provider = XMLReadCommandProvider.getInstance();

        List<Appliance> result = new ArrayList<>();
        for (Element catalogue : catalogues) {
            String commandName = catalogue.getName().split("_")[0];

            ApplianceReadCommandXML command = provider.getCommand(commandName);
            if (command instanceof WrongRequestReadCommandXML) {
                continue;
            }
            result.addAll(command.getAppliances(criteria));
        }

        return result;
    }
}
