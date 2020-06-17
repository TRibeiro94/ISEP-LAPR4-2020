package eapli.base.app.backoffice.console.presentation.rawmaterials;


import eapli.base.rawmaterialmanagement.application.RegisterRawMaterialCategoryController;
import eapli.base.rawmaterialmanagement.application.RegisterRawMaterialController;
import eapli.base.rawmaterialmanagement.domain.RawMaterialCategory;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;


public class RegisterRawMaterialUI extends AbstractUI {

    private final RegisterRawMaterialController theController = new RegisterRawMaterialController();
    private final RegisterRawMaterialCategoryController theCategoryController = new RegisterRawMaterialCategoryController();


    @Override
    protected boolean doShow() {
        final Iterable<RawMaterialCategory> rawMaterialInfo = this.theCategoryController.getRawMaterialCategory();
        final SelectWidget<RawMaterialCategory> selector = new SelectWidget<>("Select the category of the new Raw Material:", rawMaterialInfo, new RawMaterialPrinter());
        selector.show();

        final RawMaterialCategory theRawMaterialCategory = selector.selectedElement();
        if (theRawMaterialCategory == null) {
            return false;
        }
        final String code = Console.readLine("Raw Material code");
        final String technicalSheet = Console.readLine("Technical Sheet info");
        final String description = Console.readLine("Description");

        try {
            this.theController.registerRawMaterial(code, technicalSheet, description, theRawMaterialCategory);
        } catch (Exception e) {

        }

        return true;
    }

    @Override
    public String headline() {
        return "Register Raw Material";
    }
}
