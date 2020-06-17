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
@DiscriminatorValue("PDM")
public class ProductionDeliveryMessage extends Message {
    
    private String idProduct;
    
    private String quantity;
    
    private String idDeposit;
    
    private String idLote;
    
    protected ProductionDeliveryMessage(){
        super();
        //ORM
    }
    
    public ProductionDeliveryMessage(String origin, String idProductionLine, Date creationDate, String idProduct, String quantity, String idDeposit, String idLote){
        super(origin, idProductionLine, creationDate, null);
        Preconditions.noneNull(idProduct, quantity, idDeposit);
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.idDeposit = idDeposit;
        this.idLote = idLote;
    }
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ProductionDeliveryMessage)) {
            return false;
        }

        final ProductionDeliveryMessage that = (ProductionDeliveryMessage) other;
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
     * @return the idProduct
     */
    public String productId() {
        return this.idProduct;
    }
    
    /**
     * @return the idDeposit
     */
    public String depositId() {
        return this.idDeposit;
    }
    
    /**
     * @return the idLote
     */
    public String loteId() {
        return this.idLote;
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
