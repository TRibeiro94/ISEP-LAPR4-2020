/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.application;

import eapli.base.depositsmanagement.repositories.DepositRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.messagemanagement.domain.ConsumptionMessage;
import eapli.base.messagemanagement.domain.*;
import eapli.base.messagemanagement.repositories.MessageRepository;
import eapli.base.notificationmanagement.application.NotificationController;
import eapli.base.productionordermanagement.repositories.ProductionOrderRepository;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialRepository;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;

/**
 *
 * @author Bernardo Carvalho
 */
public class ValidateMessage {
    
    private static boolean parseQuantityToInteger(Message message, String quantity){
        try{
            Integer.parseInt(quantity);
        } catch(NumberFormatException e){
            NotificationController notificationController = new NotificationController();
            notificationController.newNotification("Invalid quantity! Could not parse to integer value", message.identity().toString());
            return false;
        }
        return true;
    }
    
    private static boolean checkForAlphanumericInIntegerField(Message message){
        if(message instanceof ConsumptionMessage){
            return parseQuantityToInteger(message, ((ConsumptionMessage) message).quantity());
        }
        else if(message instanceof ProductionDeliveryMessage){
            return parseQuantityToInteger(message, ((ProductionDeliveryMessage) message).quantity());
        }
        else if(message instanceof ProductionMessage){
            return parseQuantityToInteger(message, ((ProductionMessage) message).quantity());
        }
        else if(message instanceof ChargeBackMessage){
            return parseQuantityToInteger(message, ((ChargeBackMessage) message).quantity());
        }
        
        return true;
    }
    
    private static boolean checkForFutureDates(Message message) {
        Date messageDate = message.creationDate();
        Date nowDate = new Date();
//        System.out.println(messageDate);
//        System.out.println(nowDate);

        long differenceSeconds = (messageDate.getTime()-nowDate.getTime())/1000;
//        System.out.println(differenceSeconds);
        if(differenceSeconds > 0){
            NotificationController notificationController = new NotificationController();
            notificationController.newNotification("Invalid date! Date is in the future", message.identity().toString());
            return false;
        }
        
        return true;
    }
    
    private static boolean existsProductionOrder(Message message, String productionOrderCode){
        ProductionOrderRepository productionOrderRepo = PersistenceContext.repositories().productionOrderRepository();
        if(productionOrderRepo.productionOrderById(productionOrderCode) == null){
            NotificationController notificationController = new NotificationController();
            notificationController.newNotification("Invalid production order! Production order doesn't exist in the system or its not in the message and its requested to be", message.identity().toString());
            return false;
        }
        
        return true;
    }
    
    private static boolean existsRawMaterial(Message message, String rawMaterialCode){
        ProductRepository productRepo = PersistenceContext.repositories().productRepository();
        RawMaterialRepository rawMaterialRepo = PersistenceContext.repositories().rawMaterials();
        if(productRepo.productById(rawMaterialCode) == null && rawMaterialRepo.rawMaterialById(rawMaterialCode) == null){
            NotificationController notificationController = new NotificationController();
            notificationController.newNotification("Invalid raw material! RawMaterial/Product doesn't exist in the system", message.identity().toString());
            return false;
        }
        
        return true;
    }
    
    private static boolean existsProduct(Message message, String productCode){
        ProductRepository productRepo = PersistenceContext.repositories().productRepository();
        if(productRepo.productById(productCode) == null){
            NotificationController notificationController = new NotificationController();
            notificationController.newNotification("Invalid product! Product doesn't exist in the system", message.identity().toString());
            return false;
        }
        
        return true;
    }
    
    private static boolean existsDeposit(Message message, String depositCode){
        DepositRepository depositRepo = PersistenceContext.repositories().depositRepository();
        if(depositRepo.depositById(depositCode) == null){
            NotificationController notificationController = new NotificationController();
            notificationController.newNotification("Invalid deposit! Deposit doesn't exist in the system", message.identity().toString());
            return false;
        }
        
        return true;
    }

    private static boolean checkForNotSpecifiedElements(Message message) {
        if(message instanceof StartOfActivityMessage){
            return existsProductionOrder(message, ((StartOfActivityMessage) message).idProductionOrder());
        }
        else if(message instanceof EndOfActivityMessage){
            return existsProductionOrder(message, ((EndOfActivityMessage) message).idProductionOrder());
        }
        else if(message instanceof ChargeBackMessage){
            return existsRawMaterial(message, ((ChargeBackMessage) message).rawMaterialId()) 
                    && existsDeposit(message, ((ChargeBackMessage) message).depositId());
        }
        else if(message instanceof ConsumptionMessage){
            if(((ConsumptionMessage) message).depositId() == null){
                return existsRawMaterial(message, ((ConsumptionMessage) message).rawMaterialId());
            }
            else{
                return existsRawMaterial(message, ((ConsumptionMessage) message).rawMaterialId()) 
                        && existsDeposit(message, ((ConsumptionMessage) message).depositId());
            }
        }
        else if(message instanceof ProductionDeliveryMessage){
            return existsProduct(message, ((ProductionDeliveryMessage) message).productId()) 
                    && existsDeposit(message, ((ProductionDeliveryMessage) message).depositId());
        }
        else if(message instanceof ProductionMessage){
            return existsProduct(message, ((ProductionMessage) message).productId());
        }
        
        return true;
    }

    public static boolean validateMessagesForProductionLine(Iterable<Message> messages) {
        boolean validData = true;
        boolean validDates = true;
        boolean specifiedElements = true;
        boolean validMessages = true;

        for (Message message : messages) {
            //System.out.println(message);
            validData = checkForAlphanumericInIntegerField(message);
            validDates = checkForFutureDates(message);
            specifiedElements = checkForNotSpecifiedElements(message);
            
            if(!validData){
                validMessages = false;
            }
            if(!specifiedElements){
                validMessages = false;
            }
            if(!validDates){
                validMessages = false;
            }
        }
            
        return validMessages;
    }
    
    private static List<Pair<Message, Message>>  createBeginEndProductionOrders(List<Pair<Message, Message>> beginEndProductionOrders, List<Message> beginProductionOrders, List<Message> endProductionOrders){
        if(!beginProductionOrders.isEmpty()){
            for (Message m1 : beginProductionOrders) {
                for (Message m2 : endProductionOrders) {
                    if(((StartOfActivityMessage) m1).idProductionOrder().equals(((EndOfActivityMessage) m2).idProductionOrder())){
                        if(((StartOfActivityMessage) m1).origin().equals(((EndOfActivityMessage) m2).origin())){
                            beginEndProductionOrders.add(new Pair(m1, m2));
                            beginProductionOrders.remove(m1);
                            endProductionOrders.add(m2);
                            return createBeginEndProductionOrders(beginEndProductionOrders, beginProductionOrders, endProductionOrders);
                        }
                    }
                }
            }
        }
        return beginEndProductionOrders;
    }

    public static List<Pair<Message, Message>> beginEndMessagesPerMachine(Iterable<Message> messages, List<Pair<Message, Message>> beginEndProductionOrders, List<Message> beginProductionOrders, List<Message> endProductionOrders) {
        for(Message message : messages){
            if(message instanceof StartOfActivityMessage){
                beginProductionOrders.add(message);
            }
            else if(message instanceof EndOfActivityMessage){
                endProductionOrders.add(message);
            }
        }
        
        return createBeginEndProductionOrders(beginEndProductionOrders, beginProductionOrders, endProductionOrders);
    }

    public static void enrichMessages(Iterable<Message> messages, List<Pair<Message, Message>> beginEndProductionOrders) {
        MessageRepository messageRepo = PersistenceContext.repositories().messageRepository();
        
        for (Pair<Message, Message> pair : beginEndProductionOrders) {
            Date beginingDate = pair.getKey().creationDate();
            Date endDate = pair.getValue().creationDate();
            String machineCode = pair.getKey().origin();
            
            for (Message message : messages) {
                Date messageCreationDate = message.creationDate();
                long differenceFromBegin = (messageCreationDate.getTime()-beginingDate.getTime())/1000;
                long differenceFromEnd = (messageCreationDate.getTime()-endDate.getTime())/1000;
                if(differenceFromBegin > 0 && differenceFromEnd < 0 && machineCode.equalsIgnoreCase(message.origin())){
                    message.updateProductionOrderId(pair.getKey().idProductionOrder());
                    messageRepo.save(message);
                }
            }
        }
    }
    
}
