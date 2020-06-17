/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.depositsmanagement.application.DepositController;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Tiago Ribeiro
 */
public class DepositBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(DepositBootstrapper.class);

    @Override
    public boolean execute() {
        register("DEPOSIT_1", "Deposit for bolts");
        register("DEPOSIT_2", "Deposit for wood");
        register("DEPOSIT_3", "Deposit for iron");

        return true;
    }


    private void register(String depCode, String depDesc) {

        final DepositController controller = new DepositController();

        try {
            controller.specifyDeposit(depCode, depDesc);
        } catch (Exception exc) {
            System.out.println("------------------------------------------------------------------------------");
            exc.printStackTrace();
            LOGGER.warn("Assuming {} already exists (activate trace log for details)");
            LOGGER.trace("Assuming existing record", exc);
        }
    }
}
