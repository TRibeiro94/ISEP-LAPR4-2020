/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.messagemanagement.communication;

import eapli.base.Application;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bernardo Carvalho
 */
public class OpenMessagesFiles implements Runnable {
    
    @Override
    public void run(){ 
        File entrancefile = null;
        File exitfile = null;
        try{ 
            entrancefile = new File(Application.settings().getProperty("entranceDirectory"));
            entrancefile.mkdir();

            exitfile = new File(Application.settings().getProperty("exitDirectory"));
            exitfile.mkdir();
        } catch (Exception e) {
            System.out.println ("Failed opening or creating the files"); 
        }
        
        ArrayList <Thread> threads = new ArrayList<>();
        ArrayList <File> files = new ArrayList(Arrays.asList(entrancefile.listFiles()));
        for(File f : files){
            
            Thread t = new Thread(new ImportMessages(f));
            threads.add(t);
            t.start();
        }
        
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(OpenMessagesFiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (File file : files) {
            
            Path temp = null;
            try {
                temp = Files.move(Paths.get("FilesToImport\\"+file.getName()),
                            Paths.get("ProcessedFiles\\"+file.getName()));
            } catch (IOException ex) {
                Logger.getLogger(OpenMessagesFiles.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(temp == null) {
                System.out.println("Failed to move the file"); 
            }
        }
    }
    
}
