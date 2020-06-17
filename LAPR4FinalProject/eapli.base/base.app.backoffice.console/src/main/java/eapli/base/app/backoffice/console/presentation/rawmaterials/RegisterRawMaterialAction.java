package eapli.base.app.backoffice.console.presentation.rawmaterials;

import eapli.framework.actions.Action;

public class RegisterRawMaterialAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterRawMaterialUI().show();
    }

}