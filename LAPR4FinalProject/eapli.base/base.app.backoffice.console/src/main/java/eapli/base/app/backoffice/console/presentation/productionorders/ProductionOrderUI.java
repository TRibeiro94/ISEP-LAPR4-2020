package eapli.base.app.backoffice.console.presentation.productionorders;

import eapli.base.app.backoffice.console.presentation.products.ProductPrinter;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.productionordermanagement.application.ProductionOrderController;
import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Pedro Borda de Água
 */
public class ProductionOrderUI extends AbstractUI {

    private final ProductRepository repo = PersistenceContext.repositories().productRepository();
    private final ProductionOrderController controller = new ProductionOrderController();

    @Override
    protected boolean doShow() {

        final Iterable<Product> poInfo = this.repo.findAll();
        final SelectWidget<Product> selector = new SelectWidget<>("Select the product (by its code as seen below)", poInfo , new ProductPrinter());
        selector.show();

        final Product prod = selector.selectedElement();
        if (prod == null){
            return false;
        }

        String idOrder = Console.readLine("Production Order id: ");
        String emissionDate = Console.readLine("Emission Date (format yyyyMMdd): ");
        String expectedDate = Console.readLine("Expected Date (format yyyyMMdd): ");
        String productCode = prod.identity();
        String quantity = Console.readLine("Quantity: ");
        String measureUnit = Console.readLine("Units (KG / GR / LT / CL): ");
        String requestCodes = Console.readLine("Request/Order code (format: order,order): ");

        try {
            ProductionOrderController poc = new ProductionOrderController();
            poc.prodOrder(idOrder, convertStringToDate(emissionDate), convertStringToDate(expectedDate), productCode, Integer.parseInt(quantity), measureUnit, requestCodes);
        } catch (Exception e) {

        }
        
        return true;
    }

    @Override
    public String headline() {
        return "Register Production Order";
    }

    public LocalDate convertStringToDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate valueDate = LocalDate.parse(value, formatter);
        return valueDate;
    }
}
