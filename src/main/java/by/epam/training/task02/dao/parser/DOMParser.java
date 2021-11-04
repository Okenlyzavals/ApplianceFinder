package by.epam.training.task02.dao.parser;

import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Implementation of JDOMParser interface.
 * Uses DOM parser.
 */
public class DOMParser implements JDOMParser {

    /**
     * Current instance of DOMParser
     */
    private static final DOMParser instance = new DOMParser();
    /**
     * Path to the file that will be parsed.
     */
    private final String SOURCE_PATH = Objects.requireNonNull(DOMParser.class.getClassLoader().getResource("appliances.xml"), "eeeeeee blet").getPath();
    //private final String SOURCE_PATH = "D:\\appliances.xml";

    /**
     * This class is a singleton, thus it is must not be instantiated more than once.
     */
    private DOMParser() {
    }

    /**
     * Returns the current instance of {@code DOMParser}.
     *
     * @return The current instance.
     */
    public static DOMParser getInstance() {
        return instance;
    }

    @Override
    public org.jdom2.Document getDocument() {
        File xmlFile = new File(SOURCE_PATH);

        if (!xmlFile.exists()) {
            return null;
        }

        org.jdom2.Document document = null;

        try {
            document = getDocFromFile(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    @Override
    public String getPath() {
        return SOURCE_PATH;
    }

    /**
     * A method to parse document from file.
     *
     * @param file The file to be parsed.
     * @return An instance of Document.
     */
    private org.jdom2.Document getDocFromFile(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(file);
        DOMBuilder domBuilder = new DOMBuilder();

        return domBuilder.build(doc);
    }
}
