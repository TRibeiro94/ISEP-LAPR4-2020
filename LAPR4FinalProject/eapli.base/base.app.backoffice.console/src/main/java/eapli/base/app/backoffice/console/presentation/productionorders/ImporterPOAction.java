package eapli.base.app.backoffice.console.presentation.productionorders;

import eapli.framework.actions.Action;

/**
 *
 * @author Pedro Borda de Água
 */
public class ImporterPOAction implements Action {
    @Override
    public boolean execute() { return new ImporterPOUI().show(); }
}