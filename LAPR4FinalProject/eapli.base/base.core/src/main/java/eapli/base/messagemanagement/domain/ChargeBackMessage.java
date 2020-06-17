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
@DiscriminatorValue("CBM")
public class ChargeBackMessage extends Message {
    
    private String idRawMaterial;
    
    private String quantity;
    
    private String idDeposit;
    
    protected ChargeBackMessage(){
        super();
        //ORM
    }
    
    public ChargeBackMessage(String origin, String idProductionLine, Date creationDate, String idRawMaterial, String quantity, String idDeposit){
        super(origin, idProductionLine, creationDate, null);
        Preconditions.noneNull(idRawMaterial, quantity, idDeposit);
        this.idRawMaterial = idRawMaterial;
        this.quantity = quantity;
        this.idDeposit = idDeposit;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ChargeBackMessage)) {
            return false;
        }

        final ChargeBackMessage that = (ChargeBackMessage) other;
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
