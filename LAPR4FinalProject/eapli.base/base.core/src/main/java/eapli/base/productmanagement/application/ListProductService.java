package eapli.base.productmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;

/**
 * This class is used to avoid code duplication
 */
public class ListProductService {

    private final ProductRepository repo = PersistenceContext.repositories().productRepository();

    /**
     * This method checks the database for products without a Bill of Materials
     *
     * @return an Iterable of Products
     */
    public Iterable<Product> allProductsWithoutBOM() {
        return this.repo.allProductsWithoutBOM();
    }
}
