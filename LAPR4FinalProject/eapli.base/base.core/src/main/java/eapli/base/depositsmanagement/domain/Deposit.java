/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.depositsmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Tiago Ribeiro
 */
@Entity
public class Deposit implements AggregateRoot<String> {

    @Id
    private String code;

    private String description;

    protected Deposit() {
        //for ORM
    }

    /**
     * Full Constructor
     *
     * @param code        unique code
     * @param description deposit description
     */
    public Deposit(String code, String description) {
        Preconditions.noneNull(code, description);
        this.code = code;
        this.description = description;
    }

    /**
     * Identifies the deposit.
     * Replaces the standard getter
     *
     * @return the code
     */
    @Override
    public String identity() {
        return this.code;
    }

    /**
     * Allows the later alteration of the deposits description
     * Replaces the standard setter
     *
     * @param desc new description
     */
    public void changeDescription(String desc) {
        description = desc;
    }

    public String consultDepositsDescription() {
        return String.format(this.description);
    }

    /**
     * This method compares a deposit to other objects
     *
     * @param other The object to be compared
     * @return true if equal, false if not
     */
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Deposit)) {
            return false;
        }

        final Deposit newDep = (Deposit) other;
        if (this == newDep) {
            return true;
        }

        return identity().equals(newDep.identity()) && description.equals(newDep.description);
    }
}
