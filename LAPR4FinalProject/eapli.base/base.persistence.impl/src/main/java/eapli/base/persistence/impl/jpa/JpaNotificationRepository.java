/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;


import eapli.base.notificationmanagement.domain.Notification;
import eapli.base.notificationmanagement.repositories.NotificationRepository;

/**
 * @author Tiago
 */
public class JpaNotificationRepository extends BasepaRepositoryBase<Notification, String, String> implements NotificationRepository {

    public JpaNotificationRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }
}
