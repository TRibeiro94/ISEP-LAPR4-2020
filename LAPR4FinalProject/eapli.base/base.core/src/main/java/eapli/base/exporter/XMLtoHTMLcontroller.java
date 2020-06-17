/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.exporter;

/**
 * @author Tiago Ribeiro
 */
public class XMLtoHTMLcontroller {

    public void transformXMLintoHTML(String htmlFile) {
        XMLToHTMLTransformer transform = new XMLToHTMLTransformer();
        transform.XMLtoHTML(htmlFile);
    }
}
