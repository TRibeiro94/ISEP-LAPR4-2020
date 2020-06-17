/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.messages;

import eapli.base.messagemanagement.application.AnalyseInputData;
import eapli.base.messagemanagement.application.CheckProductionLineMessageProcessingStateController;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Bernardo Carvalho
 */
public class CheckProductionLineMessageProcessingStateUI extends AbstractUI{
    
    private final CheckProductionLineMessageProcessingStateController theController = new CheckProductionLineMessageProcessingStateController();

    public CheckProductionLineMessageProcessingStateUI() {
    }

    @Override
    protected boolean doShow() {
        
        AnalyseInputData ad = new AnalyseInputData();
        
        String productionLineCode = Console.readLine("Insert the code of the production line you want to check");
        ProductionLine productionLine = ad.validateProductionLineCode(productionLineCode);
        
        String state = theController.productionLineMessagesProcessingState(productionLine);
        
        System.out.println("\nThe state of the "+productionLineCode+" production line message processing is "+state+"\n");
        
        return true;
    }

    @Override
    public String headline() {
        return "Check Production Line Messages Processing State";
    }
    
}
