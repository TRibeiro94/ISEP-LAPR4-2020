/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.exporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author Tiago Ribeiro
 */
public class XMLtoTXTtransformer {

    private static final String XML_FILE = "factoryInfo.xml";
    private static final String XSL_FILE = "xslFileForTxtConversion.xsl";

    public void XMLtoTXT(String txtFile) throws FileNotFoundException, IOException, SAXException, ParserConfigurationException, TransformerException {

        File xmlSource = new File(XML_FILE);
        File stylesheet = new File(XSL_FILE);

        convertXMLToTXT(xmlSource, stylesheet, txtFile);
    }

    public static void convertXMLToTXT(File xml, File xsl, String txtFile) throws IOException, SAXException, ParserConfigurationException, TransformerConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xml);

        StreamSource stylesource = new StreamSource(xsl);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File(txtFile));
        transformer.transform(source, outputTarget);

        //System.out.println(txtFile +" was generated successfully.");
    }
}
