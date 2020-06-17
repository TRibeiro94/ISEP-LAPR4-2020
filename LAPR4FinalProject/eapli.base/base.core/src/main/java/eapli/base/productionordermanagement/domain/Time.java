/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productionordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;

/**
 *
 * @author Bernardo Carvalho
 */
public class Time implements ValueObject {

    private long hour;

    private long minute;

    private long second;

    protected Time() {
        //ORM
    }

    public Time(long hour, long minute, long second) {
        Preconditions.ensure(hour < 24 && hour >= 0 && minute >= 0 && minute < 60 && second >= 0 && second < 60);
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    public long hour(){
        return this.hour;
    }
    
    public long minute(){
        return this.minute;
    }
    
    public long second(){
        return this.second;
    }
    
    public void updateTime(long hour, long minute, long second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    public long timeToSeconds(){
        return (this.hour * 60 * 60) + (this.minute * 60) + this.second;
    }
}
