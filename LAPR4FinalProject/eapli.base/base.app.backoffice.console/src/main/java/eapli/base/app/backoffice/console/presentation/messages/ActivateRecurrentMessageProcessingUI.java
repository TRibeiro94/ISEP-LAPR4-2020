/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.messages;

import eapli.base.messagemanagement.application.ActivateRecurrentMessageProcessingController;
import eapli.base.messagemanagement.application.AnalyseInputData;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Date;

/**
 *
 * @author Bernardo Carvalho
 */
public class ActivateRecurrentMessageProcessingUI extends AbstractUI {
    
    private final ActivateRecurrentMessageProcessingController theController = new ActivateRecurrentMessageProcessingController();

    @Override
    protected boolean doShow() {
        
        AnalyseInputData ad = new AnalyseInputData();
        
        String productionLineCode = Console.readLine("Insert the code of the production line to activate its recurrent message processing");
        ProductionLine pl = ad.validateProductionLineCode(productionLineCode);
        
        String beginingDate = Console.readLine("Insert the begining date (can´t be in the future) for the recurrent message processing (yyyymmddhhmmss)");
        Date newDate = ad.validateBeginingDate(beginingDate);
        
        String interval = Console.readLine("Insert the interval in minutes for the recurrent message processing");
        Long timeInterval = ad.timeSlotInMinutes(interval);
        
        theController.updateProductionLineMessagesProcessingState(pl, newDate, timeInterval);
        
        return true;
    }

    @Override
    public String headline() {
        return "Activate recorrently message processing for a production line";
    }
    
}
