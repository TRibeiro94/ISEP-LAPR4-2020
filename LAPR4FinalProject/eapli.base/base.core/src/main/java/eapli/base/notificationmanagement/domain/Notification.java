/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.notificationmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Tiago Ribeiro
 */
@Entity
public class Notification implements AggregateRoot<String>{
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long notificationId;
    
    private String type;
    
    private String messageCode;
    
    private boolean archived;
    
    protected Notification() {
        //for ORM
    }
    
    public Notification(String type, String messageCode){
        this.type = type;
        this.messageCode = messageCode;
        archived = false;
    }
    
    @Override
    public String identity() {
        return String.valueOf(this.notificationId);
    }
    
    public String messageCode(){
        return this.messageCode;
    }
    
    public String typeOfError(){
        return this.type;
    }
    
    public void archiveNotification(){
        this.archived = true;
    }
    
    public boolean consultState(){
        return this.archived;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Notification)) {
            return false;
        }

        final Notification that = (Notification) other;
        
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public String toString() {
        return "Notification{" + "notificationId=" + notificationId + ", type=" + type + ", messageCode=" + messageCode + ", archived=" + archived + '}';
    }
    
}
