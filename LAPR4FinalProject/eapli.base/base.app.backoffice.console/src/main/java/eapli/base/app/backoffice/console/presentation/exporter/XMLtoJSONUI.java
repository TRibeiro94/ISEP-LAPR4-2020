/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.exporter;

import eapli.base.exporter.XMLtoJSONcontroller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author Tiago Ribeiro
 */
public class XMLtoJSONUI extends AbstractUI {

    private final XMLtoJSONcontroller controller = new XMLtoJSONcontroller();

    public XMLtoJSONUI() throws ParserConfigurationException {
    }

    @Override
    protected boolean doShow() {
        String file = Console.readLine("Insert the name of the destination JSON file.\n").toLowerCase();
        try {
            controller.transformXMLintoJSON(file);
        } catch (Exception ex) {
            Logger.getLogger(XMLtoJSONUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\nXML was successfully transformed into JSON.\nOriginal file was not changed.");

        return true;
    }

    @Override
    public String headline() {
        return "Transform factory floor XML into JSON.";
    }

}
