/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.depositsmanagement.application;

import eapli.base.depositsmanagement.domain.Deposit;
import eapli.base.depositsmanagement.repositories.DepositRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

/**
 * @author Tiago Ribeiro
 */
public class DepositController {
    private final DepositRepository repository = PersistenceContext.repositories().depositRepository();

    /**
     * introduces a new deposit in the DB
     *
     * @param code unique identifier
     * @param desc description
     * @return the deposit created
     */
    public Deposit specifyDeposit(String code, String desc) {
        final Deposit newDeposit = new Deposit(code, desc);

        return this.repository.save(newDeposit);
    }
}
