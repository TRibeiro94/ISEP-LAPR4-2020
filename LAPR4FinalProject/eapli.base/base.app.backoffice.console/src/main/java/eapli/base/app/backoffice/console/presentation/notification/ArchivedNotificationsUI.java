/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.notification;

import eapli.base.notificationmanagement.application.NotificationController;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author Tiago Ribeiro
 */
public class ArchivedNotificationsUI extends AbstractUI{
    
    private final NotificationController controller = new NotificationController();

    @Override
    protected boolean doShow() {
        System.out.println("Archived Notifications\n");

        try {
            controller.consultArchivedNotifications();
        } catch (Exception e) {
            System.out.println("------------------------------------------------------------------");
            e.printStackTrace();
        }
        
        return true;
    }

    @Override
    public String headline() {
        return "Consult Archived Notifications";
    }
}
