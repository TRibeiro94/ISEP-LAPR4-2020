package eapli.base.app.backoffice.console.presentation.products;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.visitor.Visitor;

public class ProductPrinter implements Visitor<Product> {

    @Override
    public void visit(Product visitee) {
        System.out.printf("Code: %s - Brief Description: %s - FullDescription: %s", visitee.identity(), visitee.getBriefDescription(), visitee.getFullDescription());
    }
}
