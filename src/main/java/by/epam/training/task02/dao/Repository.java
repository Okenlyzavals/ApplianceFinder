package by.epam.training.task02.dao;

import by.epam.training.task02.dao.parser.DOMParser;
import by.epam.training.task02.dao.parser.JDOMParser;
import org.jdom2.Document;
import org.jdom2.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Class that defines a repository that stores the XML document.
 * <p>Repository class is used both to read and to write appliances.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public class Repository {

    /**
     * A parser that is used to parse xml file into a document.
     */
    private static final JDOMParser parser = DOMParser.getInstance();
    /**
     * current instance of Repository.
     */
    private static final Repository repository = new Repository();
    /**
     * {@code org.jdom2.Document} - a storage of appliances
     */
    private Document document;

    /**
     * Default constructor. Uses parser to parse xml file into document, then stores it.
     *
     * @see DOMParser
     */
    private Repository() {
        try {
            document = parser.getDocument();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Repository getInstance() {
        return repository;
    }

    public Document getDocument() {
        return document;
    }

    /**
     * Returns elements of a document.
     *
     * @return List of child {@code org.jdom2.Elements} of documents' root element.
     */
    public List<Element> getElements() {
        return document.getRootElement().getChildren();
    }

}
