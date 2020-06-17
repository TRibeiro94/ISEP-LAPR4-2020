package eapli.base.app.backoffice.console.presentation.rawmaterials;

import eapli.base.rawmaterialmanagement.domain.RawMaterialCategory;
import eapli.framework.visitor.Visitor;

public class RawMaterialPrinter implements Visitor<RawMaterialCategory> {

    @Override
    public void visit(RawMaterialCategory visitee) {
        System.out.printf("Code: %s - Description: %s", visitee.identity(), visitee.getDescription());
    }
}