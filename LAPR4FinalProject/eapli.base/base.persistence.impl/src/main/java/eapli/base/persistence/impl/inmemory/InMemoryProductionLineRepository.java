package eapli.base.persistence.impl.inmemory;

import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.base.productionlinemanagement.repositories.ProductionLineRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryProductionLineRepository extends InMemoryDomainRepository<String, ProductionLine> implements ProductionLineRepository {

    static {
        InMemoryInitializer.init();
    }
    
    @Override
    public ProductionLine productionLineByID(String productionLineCode) {
        return null;
    }

    @Override
    public ProductionLine productionLineByStatusFalse(String productionLineCode) {
        return null;
    }
}
