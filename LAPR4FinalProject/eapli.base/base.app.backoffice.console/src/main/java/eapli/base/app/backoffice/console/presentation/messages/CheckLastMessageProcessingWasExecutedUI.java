/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.messages;

import eapli.base.messagemanagement.application.AnalyseInputData;
import eapli.base.messagemanagement.application.CheckLastMessageProcessingWasExecutedController;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Date;

/**
 *
 * @author Bernardo Carvalho
 */
public class CheckLastMessageProcessingWasExecutedUI extends AbstractUI {

    private final CheckLastMessageProcessingWasExecutedController theController = new CheckLastMessageProcessingWasExecutedController();
    
    @Override
    protected boolean doShow() {
        AnalyseInputData ad = new AnalyseInputData();
        
        String productionLineCode = Console.readLine("Insert the code of the production line to check the last time its message processing was executed");
        ProductionLine pl = ad.validateProductionLineCode(productionLineCode);
        
        Date date = theController.lastMessagesProcessingDate(pl);
        
        System.out.println("\nThe last time the message processing was executed for this production line was in "+date+"\n");
        
        return true;
    }

    @Override
    public String headline() {
        return "Last time the message processing for a production line was executed";
    }
    
}
