/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.bootstrapers.demo;


import eapli.base.notificationmanagement.application.NotificationController;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Tiago Ribeiro
 */
public class NotificationBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(NotificationBootstrapper.class);

    @Override
    public boolean execute() {
//        register("error Z test", "a.txt");
//        register("error X test", "b.txt");
//        register("error Y test", "c.txt");

        return true;
    }


    private void register(String type, String fileName) {

        final NotificationController controller = new NotificationController();

        try {
            controller.newNotification(type, fileName);
        } catch (Exception exc) {
            System.out.println("------------------------------------------------------------------------------");
            exc.printStackTrace();
            LOGGER.warn("Assuming {} already exists (activate trace log for details)");
            LOGGER.trace("Assuming existing record", exc);
        }
    }
}
