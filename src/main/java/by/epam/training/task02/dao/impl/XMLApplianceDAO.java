package by.epam.training.task02.dao.impl;

import by.epam.training.task02.dao.ApplianceDAO;
import by.epam.training.task02.dao.Repository;
import by.epam.training.task02.dao.parser.DOMParser;
import by.epam.training.task02.dao.util.CriteriaDAOUtil;
import by.epam.training.task02.dao.xmlreadcommand.ApplianceReadCommandXML;
import by.epam.training.task02.dao.xmlwritecommand.ApplianceWriteCommand;
import by.epam.training.task02.dao.xmlwritecommand.WriteCommandProvider;
import by.epam.training.task02.entity.Appliance;
import by.epam.training.task02.entity.criteria.Criteria;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Implementation of {@code ApplianceDAO} suited for working with XML files.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class XMLApplianceDAO implements ApplianceDAO {

    @Override
    public List<Appliance> find(Criteria criteria) {

        CriteriaDAOUtil.uniteDuplicateParamsIntoMultiValues(criteria);

        ApplianceReadCommandXML command = CriteriaDAOUtil.getMatchingCommand(criteria);

        return command.getAppliances(criteria);
    }

    @Override
    public boolean write(List<Appliance> appliances) {
        Repository repository = Repository.getInstance();

        Document doc = repository.getDocument();

        if (doc == null) {
            return false;
        }

        if (!doc.getRootElement().getName().equals("appliances")) {
            doc.setRootElement(new Element("appliances"));
            doc.getRootElement().getChildren().clear();
        }

        WriteCommandProvider provider = WriteCommandProvider.getInstance();

        for (Appliance appliance : appliances) {
            Element currentCatalogue = doc.getRootElement().
                    getChild(appliance.getClass().getSimpleName() + "_catalogue");

            if (currentCatalogue == null) {
                currentCatalogue = new Element(appliance.getClass().getSimpleName() + "_catalogue");
                doc.getRootElement().addContent(currentCatalogue);
            }

            ApplianceWriteCommand command = provider.getCommand(appliance.getClass().getSimpleName());
            currentCatalogue.addContent(command.makeElement(appliance));
        }

        return outputToXMLFile(doc);
    }

    /**
     * Outputs an instance of {@code org.jdom2.Document} to XML file.
     *
     * @param document The document to write.
     * @return {@code true} if write operation was successful,
     * {@code false} otherwise;
     */
    private boolean outputToXMLFile(Document document) {
        boolean isSuccessful = true;

        try (FileWriter fileWriter = new FileWriter(DOMParser.getInstance().getPath())) {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            outputter.output(document, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            isSuccessful = false;
        }

        return isSuccessful;
    }
}
