/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.processing;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.repositories.MachineRepository;
import eapli.base.messagemanagement.domain.*;
import eapli.base.messagemanagement.repositories.MessageRepository;
import eapli.base.productionordermanagement.application.LotController;
import eapli.base.productionordermanagement.domain.DetailedMachineTimes;
import eapli.base.productionordermanagement.domain.Lot;
import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.base.productionordermanagement.domain.Time;
import eapli.base.productionordermanagement.repositories.ProductionOrderRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 *
 * @author Bernardo Carvalho
 */
public class ProcessingCalculation {

    private static final ProductionOrderRepository pOrderRepo = PersistenceContext.repositories().productionOrderRepository();
    private static final MessageRepository messagesRepo = PersistenceContext.repositories().messageRepository();
    private static final MachineRepository machineRepo = PersistenceContext.repositories().machineRepository();
    
    public static void processMessages(Iterable<Message> messages, Date beginingDate, Date endDate) {
        
        for (Message message : messages) {
            makeStockMovement(message);
            createLot(message);
            updateBeginOfProductionOrderExecution(message);
            updateEndOfProductionOrderExecution(message);
            HashSet<String> workingMachines = machinesWorking(messages);
            //obtainMachinesMessages(workingMachines, beginingDate, endDate);
        }
    }
    
    private static void makeStockMovement(Message message){
        String stockMovement = null;
        if(message instanceof ConsumptionMessage){
            if(((ConsumptionMessage) message).depositId() != null){
                stockMovement = "From deposit "+((ConsumptionMessage) message).depositId()+" left "
                        +((ConsumptionMessage) message).quantity()+" units of "+((ConsumptionMessage) message).rawMaterialId();
            }
        }
        if(message instanceof ProductionDeliveryMessage){
            stockMovement = "In deposit "+((ProductionDeliveryMessage) message).depositId()+" entenred "
                        +((ProductionDeliveryMessage) message).quantity()+" units of "+((ProductionDeliveryMessage) message).productId();
        }
        if(message instanceof ChargeBackMessage){
            stockMovement = "In deposit "+((ChargeBackMessage) message).depositId()+" entered "
                        +((ChargeBackMessage) message).quantity()+" units of "+((ChargeBackMessage) message).rawMaterialId();
        }
        
        if(stockMovement != null){
            ProductionOrder po = pOrderRepo.productionOrderById(message.idProductionOrder());
            po.addStockMovement(stockMovement.toString());
            pOrderRepo.save(po);
        }
    }
    
    private static void createLot(Message message){
        LotController lc = new LotController();
        Lot lot = null;
        if(message instanceof ProductionDeliveryMessage){
                if(((ProductionDeliveryMessage) message).loteId() != null){
                    lot = lc.registerLot(((ProductionDeliveryMessage) message).loteId(), message.idProductionOrder(), 
                            Integer.parseInt(((ProductionDeliveryMessage) message).quantity()));
                }
        }
        if(message instanceof ProductionMessage){
                if(((ProductionMessage) message).loteId() != null){
                    lot = lc.registerLot(((ProductionMessage) message).loteId(), message.idProductionOrder(), 
                            Integer.parseInt(((ProductionMessage) message).quantity()));
                }
        }
        
        if(lot != null){
            ProductionOrder po = pOrderRepo.productionOrderById(message.idProductionOrder());
            po.addLot(lot);
            pOrderRepo.save(po);
        }
    }
    
    private static void updateBeginOfProductionOrderExecution(Message message){
        ProductionOrder po = pOrderRepo.productionOrderById(message.idProductionOrder());
        Date pOrderBeginDate = po.beginDate();
        Date messageCreationDate = message.creationDate();
        if(pOrderBeginDate == null){
            po.updateExecutionBeginDate(messageCreationDate);
            pOrderRepo.save(po);
        }
        else{
            long differenceFromBegin = (messageCreationDate.getTime()-pOrderBeginDate.getTime())/1000;
            if(differenceFromBegin < 0){
                po.updateExecutionBeginDate(messageCreationDate);
                pOrderRepo.save(po);
            }
        }
    }
    
    private static void updateEndOfProductionOrderExecution(Message message){
        ProductionOrder po = pOrderRepo.productionOrderById(message.idProductionOrder());
        Date pOrderEndDate = po.endDate();
        Date messageCreationDate = message.creationDate();
        if(pOrderEndDate == null){
            po.updateExecutionEndDate(messageCreationDate);
            pOrderRepo.save(po);
        }
        else{
            long differenceFromBegin = (messageCreationDate.getTime()-pOrderEndDate.getTime())/1000;
            if(differenceFromBegin > 0){
                po.updateExecutionEndDate(messageCreationDate);
                pOrderRepo.save(po);
            }
        }
    }
    
    private static HashSet<String> machinesWorking (Iterable<Message> messages){
        HashSet<String> machinesWorking = new HashSet<>();
        for (Message message : messages) {
            machinesWorking.add(message.origin());
        }
        
        return machinesWorking;
    }
    
    private static void obtainMachinesMessages(HashSet<String> workingMachines, Date beginingDate, Date endDate){
        Iterable<Message> machineMessages = new ArrayList<>();
        for (String workingMachine : workingMachines) {
            machineMessages = messagesRepo.obtainMachineMessages(workingMachine, beginingDate, endDate);
            calculateExecutionTimesOfMachine(machineMessages);
        }
    }
    
    private static void calculateExecutionTimesOfMachine(Iterable<Message> machineMessages){
        Time grossTime = machineGrossTime(machineMessages);
        
        Time effectiveTime = machineEffectiveTime(machineMessages, grossTime);
        
        ProductionOrder po = pOrderRepo.productionOrderById(machineMessages.iterator().next().idProductionOrder());
        
        Set<DetailedMachineTimes> detailedMachineTimes = po.detailedMachineTimes();
        
        for (DetailedMachineTimes detailedMachineTime : detailedMachineTimes) {
            if(detailedMachineTime.machine().protocol().equalsIgnoreCase(machineMessages.iterator().next().origin())){
                long actualGrossTimeSeconds = detailedMachineTime.grossTime().timeToSeconds();
                long actualEffectiveTimeSeconds = detailedMachineTime.effectiveTime().timeToSeconds();
                
                long newGrossTimeSeconds = actualGrossTimeSeconds + grossTime.timeToSeconds();
                long newEffectiveTimeSeconds = actualEffectiveTimeSeconds + effectiveTime.timeToSeconds();
                
                long newGrossTimeMinutes = newGrossTimeSeconds / 60;
                long newEffectiveTimeMinutes = newEffectiveTimeSeconds / 60;
                
                long newGrossTimeHours = newGrossTimeMinutes / 60;
                long newEffectiveTimeHours = newEffectiveTimeMinutes / 60;
                
                Time newGrossTime = new Time(newGrossTimeHours, newGrossTimeMinutes, newGrossTimeSeconds);
                Time newEffectiveTime = new Time(newEffectiveTimeHours, newEffectiveTimeMinutes, newEffectiveTimeSeconds);
                
                detailedMachineTime.updateGrossTime(newGrossTime);
                detailedMachineTime.updateEfectiveTime(newEffectiveTime);
                
            }
            else{
                detailedMachineTimes.add(new DetailedMachineTimes(machineRepo.machineByID(machineMessages.iterator().next().origin()), effectiveTime, grossTime));
            }
        }
        pOrderRepo.save(po);
    }
    
    private static Time machineGrossTime(Iterable<Message> machineMessages){
        Date startDate = null;
        Date finishDate = null;
        for (Message message : machineMessages) {
            Date messageCreationDate = message.creationDate();
            if(startDate == null){
                startDate = messageCreationDate;
            }
            else{
                long differenceFromBegin = (messageCreationDate.getTime()-startDate.getTime())/1000;
                if(differenceFromBegin < 0){
                    startDate = messageCreationDate;
                }
            }
            
            if(finishDate == null){
                finishDate = messageCreationDate;
            }
            else{
                long differenceFromEnd = (messageCreationDate.getTime()-finishDate.getTime())/1000;
                if(differenceFromEnd > 0){
                    finishDate = messageCreationDate;
                }
            }
        }
        
        long grossTimeSeconds = (finishDate.getTime()-startDate.getTime())/1000;
        
        long grossTimeMinutes = grossTimeSeconds / 60;
        
        long grossTimeHours = grossTimeMinutes / 60;
        
        Time grossTime = new Time(grossTimeHours, grossTimeMinutes, grossTimeSeconds);
        
        return grossTime;
    }
    
    public static Time machineEffectiveTime(Iterable<Message> machineMessages, Time grossTime){
        NavigableMap<Date, Message> stopResumeMessages = new ConcurrentSkipListMap<>();
        for (Message message : machineMessages) {
            if(message instanceof StopMessage || message instanceof ResumeActivityMessage){
                stopResumeMessages.put(message.creationDate(), message);
            }
        }
        
        long timeStoppedSeconds = 0;
        for(int i = 0; i < stopResumeMessages.size(); i++) {
            if(stopResumeMessages.get(i) instanceof StopMessage){
                long timeStopped = (stopResumeMessages.get(i+1).creationDate().getTime()-stopResumeMessages.get(i).creationDate().getTime())/1000;
                timeStoppedSeconds += timeStoppedSeconds;
            }
            i++;
        }
        
        long effectiveTimeSeconds = grossTime.timeToSeconds() - timeStoppedSeconds;
        
        long effectiveTimeMinutes = effectiveTimeSeconds / 60;
        
        long effectiveTimeHours = effectiveTimeMinutes / 60;
        
        Time effectiveTime = new Time(effectiveTimeHours, effectiveTimeMinutes, effectiveTimeSeconds);
        
        return effectiveTime;
    }
    
}
