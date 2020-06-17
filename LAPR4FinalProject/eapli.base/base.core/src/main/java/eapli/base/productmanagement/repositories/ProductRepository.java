package eapli.base.productmanagement.repositories;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.domain.repositories.DomainRepository;

public interface ProductRepository extends DomainRepository<String, Product> {

    Iterable<Product> allProductsWithoutBOM();
    
    public Product productById(String productCode);
}
