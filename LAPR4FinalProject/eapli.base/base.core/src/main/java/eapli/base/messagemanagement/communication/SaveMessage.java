/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.communication;

import eapli.base.messagemanagement.application.ImportMessagesController;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bernardo Carvalho
 */
public class SaveMessage {
    
    public static void saveMsg(String[] messageParts, String idPL){
        
        ImportMessagesController imc = new ImportMessagesController();
        
        Date creationDate = null;
        
        try{
        creationDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(messageParts[2]);
        } catch(ParseException e){
            System.out.println("Error parsing the date");
            try {
                FileWriter fr = new FileWriter("LOGErrorsFiles/logErrorsPL_"+idPL+".txt", true);
                fr.write(concatnateString(messageParts)+"\n");
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }
        
        switch (messageParts[1]) {
            case "C0":
                if(messageParts.length == 6){
                    imc.registerConsumptionMessage(messageParts[0], idPL, creationDate, messageParts[3], messageParts[4], messageParts[5]);
                }
                else if(messageParts.length == 5){
                    imc.registerConsumptionMessage(messageParts[0], idPL, creationDate, messageParts[3], messageParts[4], null);
                }
                else{
                    try {
                        FileWriter fr = new FileWriter("LOGErrorsFiles/logErrorsPL_"+idPL+".txt", true);
                        fr.write(concatnateString(messageParts)+"\n");
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "C9":
                if(messageParts.length == 7){
                    imc.registerProductionDeliveryMessage(messageParts[0], idPL, creationDate, messageParts[3], messageParts[4], messageParts[5], messageParts[6]);
                }
                else if(messageParts.length == 6){
                    imc.registerProductionDeliveryMessage(messageParts[0], idPL, creationDate, messageParts[3], messageParts[4], messageParts[5], null);
                }
                else{
                    try {
                        FileWriter fr = new FileWriter("LOGErrorsFiles/logErrorsPL_"+idPL+".txt", true);
                        fr.write(concatnateString(messageParts)+"\n");
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "P1":
                if(messageParts.length == 6){
                    imc.registerProductionMessage(messageParts[0], idPL, creationDate, messageParts[3], messageParts[4], messageParts[5]);
                }
                else if(messageParts.length == 5){
                    imc.registerProductionMessage(messageParts[0], idPL, creationDate, messageParts[3], messageParts[4], null);
                }
                else{
                    try {
                        FileWriter fr = new FileWriter("LOGErrorsFiles/logErrorsPL_"+idPL+".txt", true);
                        fr.write(concatnateString(messageParts)+"\n");
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "P2":
                if(messageParts.length == 6){
                    imc.registerChargeBackMessage(messageParts[0], idPL, creationDate, messageParts[3], messageParts[4], messageParts[5]);
                }
                else{
                    try {
                        FileWriter fr = new FileWriter("LOGErrorsFiles/logErrorsPL_"+idPL+".txt", true);
                        fr.write(concatnateString(messageParts)+"\n");
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "S0":
                if(messageParts.length == 4){
                    imc.registerStartOfActivityMessage(messageParts[0], idPL, creationDate, messageParts[3]);
                }
                else if(messageParts.length == 3){
                    imc.registerStartOfActivityMessage(messageParts[0], idPL, creationDate, null);
                }
                else{
                    try {
                        FileWriter fr = new FileWriter("LOGErrorsFiles/logErrorsPL_"+idPL+".txt", true);
                        fr.write(concatnateString(messageParts)+"\n");
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "S1":
                if(messageParts.length == 3){
                    imc.registerResumeActivityMessage(messageParts[0], idPL, creationDate);
                }
                else{
                    try {
                        FileWriter fr = new FileWriter("LOGErrorsFiles/logErrorsPL_"+idPL+".txt", true);
                        fr.write(concatnateString(messageParts)+"\n");
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "S8":
                if(messageParts.length == 4){
                    imc.registerStopMessage(messageParts[0], idPL, creationDate, messageParts[3]);
                }
                else{
                    try {
                        FileWriter fr = new FileWriter("LOGErrorsFiles/logErrorsPL_"+idPL+".txt", true);
                        fr.write(concatnateString(messageParts)+"\n");
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "S9":
                if(messageParts.length == 4){
                    imc.registerEndOfActivityMessage(messageParts[0], idPL, creationDate, messageParts[3]);
                }
                else if(messageParts.length == 3){
                    imc.registerEndOfActivityMessage(messageParts[0], idPL, creationDate, null);
                }
                else{
                    try {
                        FileWriter fr = new FileWriter("LOGErrorsFiles/logErrorsPL_"+idPL+".txt", true);
                        fr.write(concatnateString(messageParts)+"\n");
                        fr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            default:
                try {
                    FileWriter fr = new FileWriter("LOGErrorsFiles/logErrorsPL_"+idPL+".txt", true);
                    fr.write(concatnateString(messageParts)+"\n");
                    fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(SaveMessage.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        
    }
    
    private static String concatnateString(String[] messageParts){
        StringBuilder message = new StringBuilder();
        for(int i = 0; i < messageParts.length; i++){
            if(i == messageParts.length - 1){
                message.append(messageParts[i]);
            }
            else{
                message.append(messageParts[i]+";");
            }
        }
        
        return message.toString();
    }
    
}
