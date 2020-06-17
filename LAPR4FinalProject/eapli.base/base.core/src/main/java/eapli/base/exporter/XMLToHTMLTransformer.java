/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.exporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Tiago Ribeiro
 */
public class XMLToHTMLTransformer {

    private static final String XSL_FILE = "xslFileForHtmlConversion.xsl";
    private static final String XML_FILE = "factoryInfo.xml";

    public void XMLtoHTML(String htmlFile) {

        Source xml = new StreamSource(new File(XML_FILE));
        Source xslt = new StreamSource(XSL_FILE);

        convertXMLToHTML(xml, xslt, htmlFile);
    }

    public static void convertXMLToHTML(Source xml, Source xslt, String htmlFile) {
        StringWriter sw = new StringWriter();

        try {

            FileWriter fw = new FileWriter(htmlFile);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer trasform = tFactory.newTransformer(xslt);

            trasform.transform(xml, new StreamResult(sw));

            fw.write(sw.toString());
            fw.close();

            //System.out.println(htmlFile +" was generated successfully.");

        } catch (IOException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        }
    }
}
