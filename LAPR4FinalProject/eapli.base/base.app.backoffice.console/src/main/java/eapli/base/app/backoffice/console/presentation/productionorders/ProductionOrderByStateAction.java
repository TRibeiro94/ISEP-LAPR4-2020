/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.productionorders;

import eapli.framework.actions.Action;

/**
 *
 * @author Tiago Ribeiro
 */
public class ProductionOrderByStateAction implements Action{

    @Override
    public boolean execute() {
        return new ProductionOrderByStateUI().show();
    }
    
}
