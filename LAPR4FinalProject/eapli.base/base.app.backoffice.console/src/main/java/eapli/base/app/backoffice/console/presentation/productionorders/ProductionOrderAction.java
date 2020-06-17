package eapli.base.app.backoffice.console.presentation.productionorders;

import eapli.framework.actions.Action;

/**
 *
 * @author Pedro Borda de Água
 */
public class ProductionOrderAction implements Action {

    @Override
    public boolean execute() {
        return new ProductionOrderUI().show();
    }

}