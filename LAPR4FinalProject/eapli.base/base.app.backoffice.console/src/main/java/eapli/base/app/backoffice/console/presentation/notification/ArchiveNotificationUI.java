/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.notification;

import eapli.base.notificationmanagement.application.NotificationController;
import eapli.base.notificationmanagement.application.NotificationService;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Tiago Ribeiro
 */
public class ArchiveNotificationUI extends AbstractUI{
    
    private final NotificationController controller = new NotificationController();
    private final NotificationService service = new NotificationService();

    @Override
    protected boolean doShow() {
        System.out.println("Archive Notification Errors\n");
        
        try {
            String notificationToArchive = Console.readLine("Enter the ID of the Notification you wish to archive.(Type -1 to Exit)").toLowerCase();
        
            while(!(notificationToArchive.equals("-1"))){
                if(service.notificationById(notificationToArchive) == null){
                    System.out.println("There is no notification with the provided ID. You may exit and consult the Notifications.");
                }else{
                    controller.archiveNotifications(notificationToArchive);
                    System.out.printf("Notification '%s' with the following description: '%s' was archived.\n", notificationToArchive, service.notificationById(notificationToArchive).typeOfError());
                }
                notificationToArchive = Console.readLine("\nEnter the ID of the Notification you wish to archive.(Type -1 to Exit)");
            }
        } catch (Exception e) {
            System.out.println("------------------------------------------------------------------");
            e.printStackTrace();
        }
        
        return true;
    }

    @Override
    public String headline() {
        return "Archive Notification Errors";
    }
    
}
