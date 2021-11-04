package by.epam.training.task02.dao.parser;

import org.jdom2.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Interface for JDOM XML parsers.
 *
 * @author Baranovsky E. K.
 * @version 1.0
 */
public interface JDOMParser {

    /**
     * A method to read JDOM parser document from XML file.
     *
     * @return New instance of Document class if parsing was successful,
     * {@code null} otherwise
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    Document getDocument() throws ParserConfigurationException, SAXException, IOException;

    /**
     * Returns a path to the file that implementation is using.
     *
     * @return Path to the file that is being parsed.
     */
    String getPath();

}
