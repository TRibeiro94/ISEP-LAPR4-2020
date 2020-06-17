/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productionordermanagement.domain;

import eapli.base.machinemanagement.domain.Machine;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Bernardo Carvalho
 */
@Entity
public class DetailedMachineTimes implements AggregateRoot<String>{
    
    @Id
    private String detailedMachineCode;
    
    @OneToOne
    private Machine machine;
    
    private Time efectiveTime;
    
    private Time grossTime;

    public DetailedMachineTimes() {
    }
    
    public DetailedMachineTimes(Machine machine, Time efectiveTime, Time grossTime){
        Preconditions.noneNull(machine, efectiveTime, grossTime);
        this.machine = machine;
        this.efectiveTime = efectiveTime;
        this.grossTime = grossTime;
    }
    
    public Machine machine(){
        return this.machine;
    }
    
    public Time effectiveTime(){
        return this.efectiveTime;
    }
    
    public Time grossTime(){
        return this.grossTime;
    }
    
    public void updateEfectiveTime(Time effectiveTime){
        this.efectiveTime = effectiveTime;
    }
    
    public void updateGrossTime(Time grossTime){
        this.grossTime = grossTime;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof DetailedMachineTimes)) {
            return false;
        }

        final DetailedMachineTimes that = (DetailedMachineTimes) other;
        if (this == that) {
            return true;
        }

        return this.detailedMachineCode.equalsIgnoreCase(that.detailedMachineCode);
    }

    @Override
    public String identity() {
        return this.detailedMachineCode;
    }
    
}
