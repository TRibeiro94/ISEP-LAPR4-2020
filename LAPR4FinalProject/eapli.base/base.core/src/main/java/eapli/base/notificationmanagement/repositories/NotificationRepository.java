/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.notificationmanagement.repositories;


import eapli.base.notificationmanagement.domain.Notification;
import eapli.framework.domain.repositories.DomainRepository;

/**
 *
 * @author Tiago
 */
public interface NotificationRepository extends DomainRepository<String, Notification>{
    //Iterable<Notification> consultNonArchivedNotificationErrors();
}
