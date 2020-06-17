/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.messages;

import eapli.base.messagemanagement.application.AnalyseInputData;
import eapli.base.messagemanagement.application.RequestMessageProcessingController;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Date;

/**
 *
 * @author Bernardo Carvalho
 */
public class RequestMessageProcessingUI extends AbstractUI {
    
    private final RequestMessageProcessingController theController = new RequestMessageProcessingController();

    @Override
    protected boolean doShow() {
        
        AnalyseInputData ad = new AnalyseInputData();
        
        String productionLineCode = Console.readLine("Insert the code of the production line for which you wish to request a message processing");
        ProductionLine pl = ad.validateProductionLineCodeForSimpleRequest(productionLineCode);
        
        String beginingDate = Console.readLine("Insert the begining date (can´t be in the future) for the message processing (yyyymmddhhmmss)");
        Date firstDate = ad.validateBeginingDate(beginingDate);
        
        String endDate = Console.readLine("Insert the end date (can´t be in the future or smaller than the begining date) for the message processing (yyyymmddhhmmss)");
        Date lastDate = ad.validateEndDate(firstDate, endDate);

        theController.requestMessageProcessingByDatedMessages(pl, firstDate, lastDate);
        
        return true;
    }

    @Override
    public String headline() {
        return "Request message processing for a production line";
    }
    
}
