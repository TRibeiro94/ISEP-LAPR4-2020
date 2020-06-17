/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.messages;

import eapli.base.messagemanagement.application.AnalyseInputData;
import eapli.base.messagemanagement.application.DeactivateRecurrentMessageProcessingController;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Bernardo Carvalho
 */
public class DeactivateRecurrentMessageProcessingUI extends AbstractUI{

    private final DeactivateRecurrentMessageProcessingController theController = new DeactivateRecurrentMessageProcessingController();

    @Override
    protected boolean doShow() {
        AnalyseInputData ad = new AnalyseInputData();
        
        String productionLineCode = Console.readLine("Insert the code of the production line to deactivate its recurrent message processing");
        ProductionLine pl = ad.validateProductionLineCode(productionLineCode);
        
        theController.updateProductionLineMessagesProcessingState(pl);
        
        return true;
    }

    @Override
    public String headline() {
        return "Deactivate recorrently message processing for a production line";
    }
    
}
