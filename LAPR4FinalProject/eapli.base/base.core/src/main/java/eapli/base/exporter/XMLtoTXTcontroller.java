/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.exporter;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/**
 * @author Tiago Ribeiro
 */
public class XMLtoTXTcontroller {

    public void transformXMLintoTXT(String txtFile) throws IOException, FileNotFoundException, SAXException, ParserConfigurationException, TransformerException {
        XMLtoTXTtransformer transform = new XMLtoTXTtransformer();
        transform.XMLtoTXT(txtFile);
    }
}
