/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.productionordermanagement.domain.Lot;
import eapli.base.productionordermanagement.repositories.LotRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 *
 * @author Bernardo Carvalho
 */
public class InMemoryLotRepository extends InMemoryDomainRepository<String, Lot> implements LotRepository {
    
    static {
        InMemoryInitializer.init();
    }
    
}
