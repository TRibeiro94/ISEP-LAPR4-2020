/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.console.presentation.rawmaterials;

import eapli.base.rawmaterialmanagement.application.RegisterRawMaterialCategoryController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 * @author Bernardo Carvalho
 */
public class RegisterRawMaterialCategoryUI extends AbstractUI {

    private final RegisterRawMaterialCategoryController theController = new RegisterRawMaterialCategoryController();


    @Override
    protected boolean doShow() {

        String code = Console.readLine("Raw Material Category code");
        while (this.theController.checkAvailableCode(code) == false) {
            code = Console.readLine("Code already exists. Please Insert a valid Raw Material Category code");
        }
        String description = Console.readLine("Description");

        try {
            this.theController.registerRawMaterialCategory(code, description);
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String headline() {
        return "Register Raw Material Category";
    }

}
