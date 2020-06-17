/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.messagemanagement.domain.Message;
import eapli.base.messagemanagement.repositories.MessageRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import java.util.Date;


/**
 * @author Bernardo Carvalho
 */
public class InMemoryMessageRepository extends InMemoryDomainRepository<Long, Message> implements MessageRepository {

    static {
        InMemoryInitializer.init();
    }
    
    @Override
    public Iterable<Message> messageByProductionLineID(String productionLineCode) {
        return null;
    }

    @Override
    public Iterable<Message> messageBlockBetweenDates(Date firstMessageDate, Date lastMessageDate) {
        return null;
    }

    @Override
    public Iterable<Message> obtainMachineMessages(String idMachine, Date firstMessageDate, Date lastMessageDate) {
        return null;
    }

}
