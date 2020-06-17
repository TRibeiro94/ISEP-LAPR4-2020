package eapli.base.app.backoffice.console.presentation.machines;

import eapli.framework.actions.Action;

public class RegisterMachineAction implements Action {
    @Override
    public boolean execute() {
        return new RegisterMachineUI().show();
    }
}
