package eapli.base.app.backoffice.console.presentation.products;

import eapli.framework.actions.Action;

public class CheckProductsWithoutBOMAction implements Action {

    @Override
    public boolean execute() {
        return new CheckProductsWithoutBOMUI().show();
    }
}
