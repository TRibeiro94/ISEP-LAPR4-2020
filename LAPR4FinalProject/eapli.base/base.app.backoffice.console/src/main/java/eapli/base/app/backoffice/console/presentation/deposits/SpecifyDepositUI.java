/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.deposits;

import eapli.base.depositsmanagement.application.DepositController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 * @author Tiago Ribeiro
 */
public class SpecifyDepositUI extends AbstractUI {

    private final DepositController controller = new DepositController();

    @Override
    protected boolean doShow() {
        final String code = Console.readLine("Deposit code");
        final String description = Console.readLine("Description");

        try {
            this.controller.specifyDeposit(code, description);
        } catch (Exception e) {
            System.out.println("------------------------------------------------------------------");
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public String headline() {
        return "Specify New Deposit";
    }

}
