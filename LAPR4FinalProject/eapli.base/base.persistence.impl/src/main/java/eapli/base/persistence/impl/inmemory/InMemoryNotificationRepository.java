/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;


import eapli.base.notificationmanagement.domain.Notification;
import eapli.base.notificationmanagement.repositories.NotificationRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 * @author Tiago
 */
public class InMemoryNotificationRepository extends InMemoryDomainRepository<String, Notification> implements NotificationRepository {

    static {
        InMemoryInitializer.init();
    }
}
