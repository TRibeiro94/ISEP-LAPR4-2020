package eapli.base.productmanagement.application;

import eapli.base.productmanagement.domain.Product;

/**
 * This controller checks the database for products that do not have a Bill Of Materials
 */
public class CheckProductsWithoutBomController {

    private final ListProductService lps = new ListProductService();

    /**
     * This method checks the database for products that do not have a Bill of Materials
     *
     * @return an Iterable of Products
     */
    public Iterable<Product> listProductsWithoutBOM() {
        return this.lps.allProductsWithoutBOM();
    }
}
