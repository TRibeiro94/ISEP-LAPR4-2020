/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productionlinemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.base.productionlinemanagement.repositories.ProductionLineRepository;

/**
 * @author Tiago Ribeiro
 */
public class SpecifyProductionLineController {

    private final ProductionLineRepository repository = PersistenceContext.repositories().productionLineRepository();

    /**
     * registers a new production line in the database
     *
     * @param code
     * @return
     */
    public ProductionLine registerProductionLine(String code) {
        final ProductionLine newProdLine = new ProductionLine(code);

        return this.repository.save(newProdLine);
    }
}
