/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.processing;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.messagemanagement.application.ValidateMessage;
import eapli.base.messagemanagement.domain.Message;
import eapli.base.messagemanagement.repositories.MessageRepository;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.base.productionlinemanagement.repositories.ProductionLineRepository;
import eapli.framework.validations.Preconditions;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Bernardo Carvalho
 */
public class MessageProcessor implements Runnable{
    
    private ProductionLine pl;
    
    private Date beginingDate;
    
    private Date endDate;
    
    public MessageProcessor(ProductionLine pl, Date beginingDate, Date endDate){
        Preconditions.noneNull(pl, beginingDate, endDate);
        this.pl = pl;
        this.beginingDate = beginingDate;
        this.endDate = endDate;
    }

    @Override
    public void run() {
        
        MessageRepository messageRepo = PersistenceContext.repositories().messageRepository();
        Iterable<Message> messages = messageRepo.messageByProductionLineID(pl.identity());
        boolean validMessages = ValidateMessage.validateMessagesForProductionLine(messages); 

        if(validMessages){
            List<Pair<Message, Message>> beginEndProductionOrders = new ArrayList<>();
            List<Message> beginProductionOrders = new ArrayList<>();
            List<Message> endProductionOrders = new ArrayList<>();
            beginEndProductionOrders = ValidateMessage.beginEndMessagesPerMachine(messages, beginEndProductionOrders, beginProductionOrders, endProductionOrders);
            ValidateMessage.enrichMessages(messages, beginEndProductionOrders);
            Iterable<Message> messagesBlockByDates = messageRepo.messageBlockBetweenDates(beginingDate, endDate);
            
            ProcessingCalculation.processMessages(messagesBlockByDates, beginingDate, endDate);
        }
        else{
            System.out.println("Notifications were generated for the messages of this production line. Corrected the errors and try again.");
        }
        
        ProductionLineRepository productionLineRepo = PersistenceContext.repositories().productionLineRepository();
        
        pl.updateLastMessageProcessingDate(new Date());
                
        productionLineRepo.save(pl);
    }
    
}
