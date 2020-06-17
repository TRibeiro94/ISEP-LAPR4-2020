package eapli.base.exporter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLBaseExporter {

    private final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    private final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    private final Document doc = docBuilder.newDocument();
    private final Element rootElement;

    public XMLBaseExporter() throws ParserConfigurationException {
        final Element element = buildRoot();
        this.rootElement = element;
    }

    private Element buildRoot() {
        Element rootElement = doc.createElement("factory");
        doc.appendChild(rootElement);
        return rootElement;
    }

    public Document document() {
        return this.doc;
    }

    public Element rootElement() {
        return this.rootElement;
    }

    public void outputToAFile() {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("factoryInfo.xml"));
        // Output to console for testing
        //StreamResult result = new StreamResult(System.out);
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public boolean validateFile() {
        File schemaFile = new File("factoryInfo.xsd");
        Source xmlFile = new StreamSource(new File("factoryInfo.xml"));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
            return true;
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
