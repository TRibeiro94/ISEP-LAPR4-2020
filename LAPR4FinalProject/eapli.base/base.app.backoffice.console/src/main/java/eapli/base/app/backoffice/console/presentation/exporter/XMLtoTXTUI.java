/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.exporter;

import eapli.base.exporter.XMLtoTXTcontroller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author Tiago
 */
public class XMLtoTXTUI extends AbstractUI {

    private final XMLtoTXTcontroller controller = new XMLtoTXTcontroller();

    public XMLtoTXTUI() throws ParserConfigurationException {
    }

    @Override
    protected boolean doShow() {
        String file = Console.readLine("Insert the name of the destination TXT file.\n").toLowerCase();
        try {
            controller.transformXMLintoTXT(file);
        } catch (Exception ex) {
            Logger.getLogger(XMLtoTXTUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\nXML was successfully transformed into TXT.\nOriginal file was not changed.");

        return true;
    }

    @Override
    public String headline() {
        return "Transform factory floor XML into TXT.";
    }

}
