/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.communication;

import eapli.base.machinemanagement.application.ListMachineService;
import java.io.File;
import static java.lang.System.exit;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Bernardo Carvalho
 */
public class ImportMessages implements Runnable {
    
    private File file;
    
    public ImportMessages(File file){
        this.file = file;
    }
    
    @Override
    public void run(){ 
        try
        {
            Scanner reader = new Scanner(file);
            
            try {
                ListMachineService lms = new ListMachineService();
                while (reader.hasNext()) {
                    
                    String[] messageParts = reader.next().trim().split(";");
                    
                    String idPL = lms.getIDPLByIdMachine(String.valueOf(messageParts[0]));
                    
                    SaveMessage.saveMsg(messageParts, idPL);
                    
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error reader the file. Please check the corresponding text file.");
                e.printStackTrace();
                exit(0);
            }
            reader.close();
  
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    }
    
}
