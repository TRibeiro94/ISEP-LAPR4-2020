package eapli.base.app.backoffice.console.presentation.productionorders;

import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Borda de Água
 */
public class ProductionOrderPrinter implements Visitor<ProductionOrder> {

    @Override
    public void visit(ProductionOrder visitee) {
        System.out.printf("Code: %s", visitee.identity());
    }
}