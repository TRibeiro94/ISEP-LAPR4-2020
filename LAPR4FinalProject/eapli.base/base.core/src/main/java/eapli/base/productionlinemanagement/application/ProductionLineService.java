package eapli.base.productionlinemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.base.productionlinemanagement.repositories.ProductionLineRepository;

/**
 * This class is used as a service to maintain the production lines.
 */
public class ProductionLineService {

    private final ProductionLineRepository prodLineRepo = PersistenceContext.repositories().productionLineRepository();

    /**
     * This method gets all the production lines from the DataBase.
     *
     * @returns an Iterable with instances of Production Line
     */
    public Iterable<ProductionLine> allProductionLines() {
        return this.prodLineRepo.findAll();
    }
}
