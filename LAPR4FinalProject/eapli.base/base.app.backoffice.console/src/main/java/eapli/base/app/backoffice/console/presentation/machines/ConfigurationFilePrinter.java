/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.machines;

import eapli.base.machinemanagement.domain.ConfigurationFile;
import eapli.framework.visitor.Visitor;

/**
 * @author Tiago Ribeiro
 */
public class ConfigurationFilePrinter implements Visitor<ConfigurationFile> {

    @Override
    public void visit(ConfigurationFile visitee) {
        System.out.printf("%s", visitee.configFileDescription());
    }

}
