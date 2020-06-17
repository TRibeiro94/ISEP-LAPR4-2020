/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.notificationmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.notificationmanagement.domain.Notification;
import eapli.base.notificationmanagement.repositories.NotificationRepository;

/**
 *
 * @author Tiago Ribeiro
 */
public class NotificationService {
    
    private final NotificationRepository repository = PersistenceContext.repositories().notificationRepository();
    
    /**
     * This method checks the database for products without a Bill of Materials
     *
     * @return a notification iterable
     */
    public Iterable<Notification> consultNonArchivedNotificationErrors() {
        
        for (Notification n : repository.findAll()) {
            if(n.consultState() == false){
                System.out.printf("Notification ID: %s\tError Found: %s\tCode of the Message: %s\n",n.identity(), n.typeOfError(), n.messageCode());
            }
        }
        return this.repository.findAll();
    }
    
    public Notification notificationById(String notificationId){
        for (Notification n : repository.findAll()) {
            if(n.identity().equals((notificationId).toLowerCase())){
                return (Notification) n;
            }
        }
        return null;
    }
    
    public boolean archiveNotificationErrors(String notificationId){
        try{
            Notification n = notificationById(notificationId);
            n.archiveNotification();
            repository.save(n);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public Iterable<Notification> consultArchivedNotifications() {
        
        for (Notification n : repository.findAll()) {
            if(n.consultState() == true){
                System.out.printf("Notification ID: %s\tError Found & Fixed: %s\tCode of the message: %s\n" ,n.identity(), n.typeOfError(), n.messageCode());
            }
            
        }
        return this.repository.findAll();
    }
}
