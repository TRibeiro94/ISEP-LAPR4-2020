/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Bernardo Carvalho
 */
@Entity
public class Order implements AggregateRoot<String> {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String idOrder;
    private String idProduct;
    
    private int quantity;

    protected Order() {
        //for ORM
    }

    public Order(String idOrder){
        this.idOrder = idOrder;
    }

    public Order(String idProduct, int quantity){
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Order)) {
            return false;
        }

        final Order order = (Order) other;
        if (this == order) {
            return true;
        }

        return identity().equals(order.identity());
    }

    @Override
    public String identity() {
        return this.idOrder;
    }
    
}
