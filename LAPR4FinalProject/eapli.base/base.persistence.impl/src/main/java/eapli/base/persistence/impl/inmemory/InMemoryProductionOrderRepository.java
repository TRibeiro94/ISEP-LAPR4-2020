package eapli.base.persistence.impl.inmemory;

import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.base.productionordermanagement.repositories.ProductionOrderRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.time.LocalDate;
import java.util.List;

public class InMemoryProductionOrderRepository extends InMemoryDomainRepository<String, ProductionOrder> implements ProductionOrderRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<ProductionOrder> allProductionOrder() {
        throw new UnsupportedOperationException("To be implemented");
    }

    @Override
    public Iterable<ProductionOrder> productionOrdersBetweenDates(LocalDate leftDateProdOrder, LocalDate rightDateProdOrder) {
        return null;
    }
    
    @Override
    public ProductionOrder productionOrderById(String productionOrderCode) {
        return null;
    }
    
    @Override
    public List<ProductionOrder> productionOrderByState(boolean state){
        return null;
    }
}
