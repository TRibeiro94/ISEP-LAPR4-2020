/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.depositsmanagement.domain.Deposit;
import eapli.base.depositsmanagement.repositories.DepositRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 * @author Tiago Ribeiro
 */
public class InMemoryDepositRepository extends InMemoryDomainRepository<String, Deposit> implements DepositRepository {

    static {
        InMemoryInitializer.init();
    }
    
    @Override
    public Deposit depositById(String depositCode) {
        return null;
    }

}
