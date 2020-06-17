/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.smm;

import eapli.base.smm.controller.SMMController;
import eapli.framework.presentation.console.AbstractUI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Ribeiro
 */
public class ResetRequestUI extends AbstractUI{
    
    private final SMMController controller = new SMMController();

    @Override
    protected boolean doShow() {
        try {
            controller.resetRequest();
        } catch (Exception ex) {
            Logger.getLogger(SMMUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public String headline() {
        return "Reset Request";
    }
}
