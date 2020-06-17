/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.base.productionlinemanagement.repositories.ProductionLineRepository;
import eapli.framework.util.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.NoResultException;

/**
 *
 * @author Bernardo Carvalho
 */
public class AnalyseInputData {
    
    private final ProductionLineRepository productionLineRepo = PersistenceContext.repositories().productionLineRepository();
    
    /**
     * 
     * @param productionLineCode
     * @return pl
     */
    public ProductionLine validateProductionLineCode(String productionLineCode){
        ProductionLine pl = null;
        while(pl == null){
            try{
                pl = productionLineRepo.productionLineByID(productionLineCode);
            } catch(NoResultException e){
                productionLineCode = Console.readLine("Invalid Code! Insert the code of the production line to activate its recurrent message processing");
            }
        }
        
        return pl;
    }
    
    public ProductionLine validateProductionLineCodeForSimpleRequest(String productionLineCode){
        boolean validCode = false;
        ProductionLine pl = null;
        while(!validCode){
            try{
                pl = productionLineRepo.productionLineByID(productionLineCode);
                if(pl.isMessageProcessingActive()){
                    productionLineCode = Console.readLine("The message processing for the production line you insert is already running. Try again");
                }
                else{
                    validCode = true;
                }
            } catch(NoResultException e){
                productionLineCode = Console.readLine("Invalid Code! Insert the code of the production line to activate its recurrent message processing");
            }
        }
        
        return pl;
    }
    
    /**
     * 
     * @param beginingDate
     * @return firstDate
     */
    public Date validateBeginingDate(String beginingDate){
        Date firstDate = null;
        boolean validDate = false;
        while(!validDate){
            try{
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
                Date d = new Date();
                firstDate = format.parse(beginingDate);
                long differenceInSeconds = (firstDate.getTime()-d.getTime())/1000;
                if(differenceInSeconds < 0){
                    validDate = true;
                }
                else{
                    beginingDate = Console.readLine("Invalid Date! Insert the begining date (can´t be in the future) for the recurrent message processing (yyyymmddhhmmss)");
                }
            } catch(ParseException e){
                beginingDate = Console.readLine("Invalid Date! Insert the begining date (can´t be in the future) for the recurrent message processing (yyyymmddhhmmss)");
            }
            
        }
        
        return firstDate;
    }
    
    /**
     * 
     * @param beginingDate
     * @param endDate
     * @return lastDate
     */
    public Date validateEndDate(Date beginingDate, String endDate){
        Date lastDate = null;
        boolean validDate = false;
        while(!validDate){
            try{
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
                Date d = new Date();
                lastDate = format.parse(endDate);
                long differenceInSeconds = (lastDate.getTime()-beginingDate.getTime())/1000;
                long differenceInSeconds2 = (lastDate.getTime()-d.getTime())/1000;
                if(differenceInSeconds > 0 && differenceInSeconds2 < 0){
                    validDate = true;
                }
                else{
                    endDate = Console.readLine("Invalid Date! Insert the end date (can´t be in the future or smaller than the begining date) for the message processing (yyyymmddhhmmss)");
                }
            } catch(ParseException e){
                endDate = Console.readLine("Invalid Date! Insert the end date (can´t be in the future or smaller than the begining date) for the message processing (yyyymmddhhmmss)");
            }
            
        }
        
        return lastDate;
    }
    
    /**
     * 
     * @param minutes
     * @return 
     */
    public Long timeSlotInMinutes(String minutes){
        
        Long timeSlot = null;
        boolean validMinutes = false;
        while(!validMinutes){
            try{
                timeSlot = Long.parseLong(minutes);
                if(timeSlot > 0){
                    validMinutes = true;
                }
                else{
                    minutes = Console.readLine("Invalid time slot! Insert a time slot in minutes(only numbers, can't be negative or 0)");
                }
            } catch(NumberFormatException e){
                minutes = Console.readLine("Invalid time slot! Insert a time slot in minutes(only numbers, can't be negative or 0)");
            }
        }
        
        return timeSlot * 60000;
    }
    
}
