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
@DiscriminatorValue("STM")
public class StopMessage extends Message {
    
    private static enum idErrorValues{
        ER1 { @Override public String toString() { return "interrupt requested by the controller"; } },
        ER2 { @Override public String toString() { return "malfunction"; } },
        ER3 { @Override public String toString() { return "insuficient raw material"; } };
    }
    
    private String idError;
    
    protected StopMessage(){
        super();
        //ORM
    }
    
    public StopMessage(String origin, String idProductionLine, Date creationDate, String idError){
        super(origin, idProductionLine, creationDate, null);
        if(validate(idError) == true){
            this.idError = idError;
        }else{
            this.idError = StopMessage.idErrorValues.ER2.toString();
        }
    }
    
    /**
     * validates if the inserted idError is within the desired parameters
     * @param idError
     * @return 
     */
    public static boolean validate(String idError){
        for (Object value : StopMessage.idErrorValues.values()) {
            if(value.toString().equals(idError.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof StopMessage)) {
            return false;
        }

        final StopMessage that = (StopMessage) other;
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
