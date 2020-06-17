/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.messagemanagement.domain.Message;
import eapli.base.messagemanagement.repositories.MessageRepository;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


/**
 * @author Bernardo Carvalho
 */
public class JpaMessageRepository extends BasepaRepositoryBase<Message, Long, Long> implements MessageRepository {

    public JpaMessageRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }
    
    @Override
    public synchronized Iterable<Message> messageByProductionLineID(String productionLineCode) throws NoResultException{
        boolean notProcessedMessages = false;
        final TypedQuery<Message> query = createQuery("SELECT m FROM Message m WHERE m.idProductionLine = :productionLineId AND m.processedStatus = :status", Message.class);
        query.setParameter("productionLineId", productionLineCode);
        query.setParameter("status", notProcessedMessages);
        try{
            return query.getResultList();
        } catch(NoResultException e){
            return new ArrayList();
        }
    }
    
    @Override
    public Iterable<Message> messageBlockBetweenDates(Date firstMessageDate, Date lastMessageDate) {
        boolean notProcessedMessages = false;
        final TypedQuery<Message> query = createQuery("SELECT m FROM Message m WHERE m.processedStatus = :status AND m.creationDate BETWEEN :beginDate AND :endDate", Message.class);
        query.setParameter("beginDate", firstMessageDate);
        query.setParameter("endDate", lastMessageDate);
        query.setParameter("status", notProcessedMessages);
        try{
            return query.getResultList();
        } catch(NoResultException e){
            return new ArrayList();
        }
    }
    
    @Override
    public Iterable<Message> obtainMachineMessages(String idMachine, Date firstMessageDate, Date lastMessageDate) {
        boolean notProcessedMessages = false;
        final TypedQuery<Message> query = createQuery("SELECT m FROM Message m WHERE m.processedStatus = :status AND m.origin = :machineCode AND m.creationDate BETWEEN :beginDate AND :endDate", Message.class);
        query.setParameter("beginDate", firstMessageDate);
        query.setParameter("endDate", lastMessageDate);
        query.setParameter("status", notProcessedMessages);
        query.setParameter("machineCode", idMachine);
        try{
            return query.getResultList();
        } catch(NoResultException e){
            return new ArrayList();
        }
    }

}
