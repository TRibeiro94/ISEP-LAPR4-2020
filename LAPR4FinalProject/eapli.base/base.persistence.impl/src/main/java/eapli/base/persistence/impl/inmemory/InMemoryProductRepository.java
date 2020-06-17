package eapli.base.persistence.impl.inmemory;

import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.repositories.ProductRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryProductRepository extends InMemoryDomainRepository<String, Product> implements ProductRepository {

    static {
        InMemoryInitializer.init();
    }

    //To implement later
    @Override
    public Iterable<Product> allProductsWithoutBOM() {
        throw new UnsupportedOperationException("To be implemented");
    }

    @Override
    public Product productById(String productCode) {
        return null;
    }
}
