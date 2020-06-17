/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.messagemanagement.domain.ChargeBackMessage;
import eapli.base.messagemanagement.domain.ConsumptionMessage;
import eapli.base.messagemanagement.domain.EndOfActivityMessage;
import eapli.base.messagemanagement.domain.Message;
import eapli.base.messagemanagement.domain.ProductionDeliveryMessage;
import eapli.base.messagemanagement.domain.ProductionMessage;
import eapli.base.messagemanagement.domain.ResumeActivityMessage;
import eapli.base.messagemanagement.domain.StartOfActivityMessage;
import eapli.base.messagemanagement.domain.StopMessage;
import eapli.base.messagemanagement.repositories.MessageRepository;
import java.util.Date;

/**
 *
 * @author Bernardo Carvalho
 */
public class ImportMessagesController {
    
    private final MessageRepository messageRepo = PersistenceContext.repositories().messageRepository();
    
    public boolean isSameAs(Message message){
        for(Message m : this.messageRepo.findAll()){
            if(m.sameAs(message)){
                return true;
            }
        }
        return false;
    }
    
    public void registerChargeBackMessage(String origin, String idProductionLine, Date creationDate, String idRawMaterial, String quantity, String idDeposit) {
        Message newM = new ChargeBackMessage(origin, idProductionLine, creationDate, idRawMaterial, quantity, idDeposit);
        if(!isSameAs(newM)){
            this.messageRepo.save(newM);
        }
    }
    
    public void registerConsumptionMessage(String origin, String idProductionLine, Date creationDate, String idRawMaterial, String quantity, String idDeposit) {
        Message newM = new ConsumptionMessage(origin, idProductionLine, creationDate, idRawMaterial, quantity, idDeposit);
        if(!isSameAs(newM)){
            this.messageRepo.save(newM);
        }
    }
    
    public void registerEndOfActivityMessage(String origin, String idProductionLine, Date creationDate, String idProductionOrder) {
        Message newM = new EndOfActivityMessage(origin, idProductionLine, creationDate, idProductionOrder);
        if(!isSameAs(newM)){
            this.messageRepo.save(newM);
        }
    }
    
    public void registerProductionDeliveryMessage(String origin, String idProductionLine, Date creationDate, String idProduct, String quantity, String idDeposit, String idLote) {
        Message newM = new ProductionDeliveryMessage(origin, idProductionLine, creationDate, idProduct, quantity, idDeposit, idLote);
        if(!isSameAs(newM)){
            this.messageRepo.save(newM);
        }
    }
    
    public void registerProductionMessage(String origin, String idProductionLine, Date creationDate, String idProduct, String quantity, String idLote) {
        Message newM = new ProductionMessage(origin, idProductionLine, creationDate, idProduct, quantity, idLote);
        if(!isSameAs(newM)){
            this.messageRepo.save(newM);
        }
    }
    
    public void registerResumeActivityMessage(String origin, String idProductionLine, Date creationDate) {
        Message newM = new ResumeActivityMessage(origin, idProductionLine, creationDate);
        if(!isSameAs(newM)){
            this.messageRepo.save(newM);
        }
    }
    
    public void registerStartOfActivityMessage(String origin, String idProductionLine, Date creationDate, String idProductionOrder) {
        Message newM = new StartOfActivityMessage(origin, idProductionLine, creationDate, idProductionOrder);
        if(!isSameAs(newM)){
            this.messageRepo.save(newM);
        }
    }
    
    public void registerStopMessage(String origin, String idProductionLine, Date creationDate, String idError) {
        Message newM = new StopMessage(origin, idProductionLine, creationDate, idError);
        if(!isSameAs(newM)){
            this.messageRepo.save(newM);
        }
    }
    
}
