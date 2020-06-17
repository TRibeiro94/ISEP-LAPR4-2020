/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productionordermanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Bernardo Carvalho
 */
@Entity
public class Lot implements AggregateRoot<String>{
    
    @Id
    private String idLot;
    private String idProductionOrder;
    private Integer quantity;

    protected Lot() {
        //for ORM
    }

    public Lot(String idLot, String idProductionOrder, Integer quantity){
        this.idLot = idLot;
        this.idProductionOrder = idProductionOrder;
        this.quantity = quantity;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Lot)) {
            return false;
        }

        final Lot that = (Lot) other;
        if (this == that) {
            return true;
        }

        return this.identity().equalsIgnoreCase(that.identity());
    }

    @Override
    public String identity() {
        return this.idLot;
    }
    
}
