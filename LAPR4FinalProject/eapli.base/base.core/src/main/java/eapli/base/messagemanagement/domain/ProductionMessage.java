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
@DiscriminatorValue("PRM")
public class ProductionMessage extends Message {
    
    private String idProduct;
    
    private String quantity;
    
    private String idLote;
    
    protected ProductionMessage(){
        super();
        //ORM
    }
    
    public ProductionMessage(String origin, String idProductionLine, Date creationDate, String idProduct, String quantity, String idLote){
        super(origin, idProductionLine, creationDate, null);
        Preconditions.noneNull(idProduct, quantity);
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.idLote = idLote;
    }
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ProductionMessage)) {
            return false;
        }

        final ProductionMessage that = (ProductionMessage) other;
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
