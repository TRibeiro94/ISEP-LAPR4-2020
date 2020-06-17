/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.depositsmanagement.domain.Deposit;
import eapli.base.depositsmanagement.repositories.DepositRepository;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * @author Tiago Ribeiro
 */
public class JpaDepositRepository extends BasepaRepositoryBase<Deposit, String, String> implements DepositRepository {

    public JpaDepositRepository(String persistenceUnitName) {
        super(persistenceUnitName, "code");
    }
    
    @Override
    public Deposit depositById(String depositCode){
        final TypedQuery<Deposit> query = createQuery("SELECT m FROM Deposit m WHERE m.code = :dCode", Deposit.class);
        query.setParameter("dCode", depositCode);
        try{
            return query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }

}
