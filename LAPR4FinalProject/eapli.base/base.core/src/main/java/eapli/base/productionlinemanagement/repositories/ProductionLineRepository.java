package eapli.base.productionlinemanagement.repositories;

import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.framework.domain.repositories.DomainRepository;

public interface ProductionLineRepository extends DomainRepository<String, ProductionLine> {

    public ProductionLine productionLineByID(String productionLineCode);
    
    public ProductionLine productionLineByStatusFalse(String productionLineCode);
    
}
