/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.depositsmanagement.repositories;

import eapli.base.depositsmanagement.domain.Deposit;
import eapli.framework.domain.repositories.DomainRepository;

/**
 * @author Tiago Ribeiro
 */
public interface DepositRepository extends DomainRepository<String, Deposit> {

    public Deposit depositById(String depositCode);
}
