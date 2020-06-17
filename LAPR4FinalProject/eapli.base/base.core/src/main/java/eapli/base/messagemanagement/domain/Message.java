/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Bernardo Carvalho
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="QUALIFICATOR")
public abstract class Message implements AggregateRoot<Long> {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String origin;
    
    private String idProductionLine;
    
    private Date creationDate;
    
    private String idProductionOrder;
    
    private boolean processedStatus;
    
    public Message(String origin, String idProductionLine, Date creationDate, String idProductionOrder){
        Preconditions.noneNull(origin, creationDate);
        this.origin = origin;
        this.idProductionLine = idProductionLine;
        this.creationDate = creationDate;
        this.idProductionOrder = idProductionOrder;
        this.processedStatus = false;
    }
    
    protected Message(){
        //ORM
    }
    
    public Long messageId(){
        return this.id;
    }
    
    public String origin(){
        return this.origin;
    }
    
    public Date creationDate(){
        return this.creationDate;
    }
    
    public String idProductionOrder(){
        return this.idProductionOrder;
    }
    
    public void updateStatusToProcessed(){
        this.processedStatus = true;
    }
    
    public void updateProductionOrderId(String idProductionOrder){
        this.idProductionOrder = idProductionOrder;
    }
    
}
