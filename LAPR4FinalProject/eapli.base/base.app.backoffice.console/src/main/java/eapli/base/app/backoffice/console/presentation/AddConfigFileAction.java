package eapli.base.app.backoffice.console.presentation;

import eapli.base.app.backoffice.console.presentation.machines.AddAConfigFileUI;
import eapli.framework.actions.Action;

public class AddConfigFileAction implements Action {
    @Override
    public boolean execute() {
        return new AddAConfigFileUI().show();
    }
}
