/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.exporter;

import eapli.base.exporter.XMLtoHTMLcontroller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author Tiago Ribeiro
 */
public class XMLtoHTMLUI extends AbstractUI {

    private final XMLtoHTMLcontroller controller = new XMLtoHTMLcontroller();

    public XMLtoHTMLUI() throws ParserConfigurationException {
    }

    @Override
    protected boolean doShow() {
        String file = Console.readLine("Insert the name of the destination HTML file.\n").toLowerCase();
        try {
            controller.transformXMLintoHTML(file);
        } catch (Exception ex) {
            Logger.getLogger(XMLtoHTMLUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("XML was successfully transformed into HTML.\nOriginal file was not changed.");

        return true;
    }

    @Override
    public String headline() {
        return "Transform factory floor XML into HTML.";
    }

}
