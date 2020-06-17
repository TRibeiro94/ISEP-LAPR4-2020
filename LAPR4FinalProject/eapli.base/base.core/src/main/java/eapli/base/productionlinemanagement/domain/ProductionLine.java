package eapli.base.productionlinemanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import java.util.Date;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductionLine implements AggregateRoot<String> {

    @Id
    private String code;
    
    private boolean messageProcessingStatus;
    
    private Date lastMessageProcessingDate;

    protected ProductionLine() {
        //for ORM
    }
    
    public ProductionLine(String code){
        Preconditions.noneNull(code);
        this.code = code;
        this.messageProcessingStatus = false;
        this.lastMessageProcessingDate = null;
    }

    @Override
    public boolean sameAs(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProductionLine other = (ProductionLine) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String identity() {
        return this.code;
    }
    
    /**
     * @return the messageProcessingStatus
     */
    public boolean isMessageProcessingActive() {
        return this.messageProcessingStatus;
    }
    
    /**
     * 
     * @return lastMessageProcessing
     */
    public Date lastMessageProcessingDate(){
        return this.lastMessageProcessingDate;
    }
    
    /**
     * 
     */
    public void activateMessageProcessing() {
        this.messageProcessingStatus = true;
    }

    /**
     * 
     */
    public void deactivateMessageProcessing() {
        this.messageProcessingStatus = false;
    }
    
    /**
     * 
     * @param newMessageProcessingDate 
     */
    public void updateLastMessageProcessingDate(Date newMessageProcessingDate){
        this.lastMessageProcessingDate = newMessageProcessingDate;
    }
}
