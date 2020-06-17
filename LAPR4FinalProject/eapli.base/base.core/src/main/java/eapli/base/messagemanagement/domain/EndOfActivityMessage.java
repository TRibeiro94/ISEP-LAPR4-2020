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
@DiscriminatorValue("EAM")
public class EndOfActivityMessage extends Message {
    
    protected EndOfActivityMessage(){
        super();
        //ORM
    }
    
    public EndOfActivityMessage(String origin, String idProductionLine, Date creationDate, String idProductionOrder){
        super(origin, idProductionLine, creationDate, idProductionOrder);
    }
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof EndOfActivityMessage)) {
            return false;
        }

        final EndOfActivityMessage that = (EndOfActivityMessage) other;
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
