package eapli.base.app.backoffice.console.presentation.products;

import eapli.base.productmanagement.application.CheckProductsWithoutBomController;
import eapli.base.productmanagement.domain.Product;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;

public class CheckProductsWithoutBOMUI extends AbstractUI {

    private final CheckProductsWithoutBomController theController = new CheckProductsWithoutBomController();

    @Override
    protected boolean doShow() {

        final Iterable<Product> products = this.theController.listProductsWithoutBOM();
        ListWidget<Product> lister = new ListWidget<>("Products", products, new ProductPrinter());
        lister.show();
        return true;
    }

    @Override
    public String headline() {
        return "Check Products Without Bill Of Materials";
    }
}
