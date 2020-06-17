/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.domain;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Bernardo Carvalho
 */
@Entity
@DiscriminatorValue("RAM")
public class ResumeActivityMessage extends Message{
    
    protected ResumeActivityMessage(){
        super();
        //ORM
    }
    
    public ResumeActivityMessage(String origin, String idProductionLine, Date creationDate){
        super(origin, idProductionLine, creationDate, null);
    }
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ResumeActivityMessage)) {
            return false;
        }

        final ResumeActivityMessage that = (ResumeActivityMessage) other;
        if (this == that) {
            return true;
        }

        return this.origin().equalsIgnoreCase(that.origin()) && !(this.creationDate().after(that.creationDate())
                || this.creationDate().before(that.creationDate()));
    }

    @Override
    public Long identity() {
        return super.messageId();
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String idProductionOrder(){
        return super.idProductionOrder();
    }
    
    /**
     * 
     * @param idProductionOrder 
     */
    @Override
    public void updateProductionOrderId(String idProductionOrder){
        super.updateProductionOrderId(idProductionOrder);
    }
    
}
