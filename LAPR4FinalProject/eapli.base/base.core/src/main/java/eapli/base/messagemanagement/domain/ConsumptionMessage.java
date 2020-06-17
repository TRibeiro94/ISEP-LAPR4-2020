/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.domain;

import eapli.framework.validations.Preconditions;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Bernardo Carvalho
 */
@Entity
@DiscriminatorValue("CNM")
public class ConsumptionMessage extends Message{
    
    private String idRawMaterial;
    
    private String quantity;
    
    private String idDeposit;
    
    protected ConsumptionMessage(){
        //ORM
        super();
    }
    
    public ConsumptionMessage(String origin, String idProductionLine, Date creationDate, String idRawMaterial, String quantity, String idDeposit){
        super(origin, idProductionLine, creationDate, null);
        Preconditions.noneNull(idRawMaterial, quantity);
        this.idRawMaterial = idRawMaterial;
        this.quantity = quantity;
        this.idDeposit = idDeposit;
    }
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ConsumptionMessage)) {
            return false;
        }

        final ConsumptionMessage that = (ConsumptionMessage) other;
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
     * @return quantity
     */
    public String quantity() {
        return this.quantity;
    }

    /**
     * @return the idRawMaterial
     */
    public String rawMaterialId() {
        return this.idRawMaterial;
    }
    
    /**
     * @return the idDeposit
     */
    public String depositId() {
        return this.idDeposit;
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
