/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.repositories;

import eapli.base.messagemanagement.domain.Message;
import eapli.framework.domain.repositories.DomainRepository;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bernardo Carvalho
 */
public interface MessageRepository extends DomainRepository<Long, Message>{
    
    public Iterable<Message> messageByProductionLineID(String productionLineCode);
    
    public Iterable<Message> messageBlockBetweenDates(Date firstMessageDate, Date lastMessageDate);
    
    public Iterable<Message> obtainMachineMessages(String idMachine, Date firstMessageDate, Date lastMessageDate);
    
}
