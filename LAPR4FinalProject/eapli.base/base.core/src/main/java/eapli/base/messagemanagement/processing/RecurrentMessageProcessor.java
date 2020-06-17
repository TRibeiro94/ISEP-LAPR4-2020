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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javafx.util.Pair;

/**
 *
 * @author Bernardo Carvalho
 */
public class RecurrentMessageProcessor implements Runnable{
    
    private ProductionLine pl;
    
    private Date beginingDate;
    
    private Long timeInterval;
    
    public RecurrentMessageProcessor(ProductionLine pl, Date beginingDate, Long timeInterval){
        Preconditions.noneNull(pl, beginingDate, timeInterval);
        this.pl = pl;
        this.beginingDate = beginingDate;
        this.timeInterval = timeInterval;
    }

    @Override
    public void run() {
        ProductionLineRepository productionLineRepo = PersistenceContext.repositories().productionLineRepository();
        MessageRepository messageRepo = PersistenceContext.repositories().messageRepository();
        
        pl.activateMessageProcessing();
            
        pl.updateLastMessageProcessingDate(new Date());
                
        productionLineRepo.save(pl);
        
        while(productionLineRepo.productionLineByStatusFalse(pl.identity()) == null){
        long secondsToAdd = timeInterval * 60;
        long startDateInSeconds = beginingDate.getTime();
        
        long t = beginingDate.getTime();
        Date endDate = new Date(t + (timeInterval * 360));
        
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
        
        beginingDate = endDate;
        
        }
        
    }
    
}
