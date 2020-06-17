/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.productionordermanagement.domain.DetailedMachineTimes;
import eapli.base.productionordermanagement.repositories.DetailedMachineTimesRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 *
 * @author Bernardo Carvalho
 */
public class InMemoryDetailedMachineTimesRepository extends InMemoryDomainRepository<String, DetailedMachineTimes> implements DetailedMachineTimesRepository{
    
    static {
        InMemoryInitializer.init();
    }
    
}
