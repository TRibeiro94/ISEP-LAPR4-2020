/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.exporter;

import eapli.framework.actions.Action;

import javax.xml.parsers.ParserConfigurationException;

/**
 * @author Tiago Ribeiro
 */
public class XMLtoHTMLAction implements Action {

    @Override
    public boolean execute() {
        try {
            return new XMLtoHTMLUI().show();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return false;
    }
}
