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
public class NotificationController {
    
    private final NotificationRepository repository = PersistenceContext.repositories().notificationRepository();
    private final NotificationService service = new NotificationService();
             
    public Notification newNotification(String type, String messageCode) {
        final Notification newNot = new Notification(type, messageCode);
        
        return this.repository.save(newNot);
    }
    
    public void consultNonArchivedNotificationErrors(){
        service.consultNonArchivedNotificationErrors();
    }
    
    public boolean archiveNotifications(String notificationId){
        return service.archiveNotificationErrors(notificationId);
        
    }
    
    public void consultArchivedNotifications(){
        service.consultArchivedNotifications();
    }
}
