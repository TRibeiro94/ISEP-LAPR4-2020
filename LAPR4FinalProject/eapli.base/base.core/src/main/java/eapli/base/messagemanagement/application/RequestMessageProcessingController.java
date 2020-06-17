/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.application;

import eapli.base.messagemanagement.processing.MessageProcessor;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import java.util.Date;

/**
 *
 * @author Bernardo Carvalho
 */
public class RequestMessageProcessingController {

    public void requestMessageProcessingByDatedMessages(ProductionLine productionLine, Date beginingDate, Date endDate) {
        
        new Thread(new MessageProcessor(productionLine, beginingDate, endDate)).start();
        
    }
    
}
