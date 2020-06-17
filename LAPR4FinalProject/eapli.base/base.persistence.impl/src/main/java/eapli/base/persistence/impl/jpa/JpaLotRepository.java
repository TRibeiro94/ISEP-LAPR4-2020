/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.productionordermanagement.domain.Lot;
import eapli.base.productionordermanagement.repositories.LotRepository;

/**
 *
 * @author Bernardo Carvalho
 */
public class JpaLotRepository extends BasepaRepositoryBase<Lot, String, String> implements LotRepository{
    
    public JpaLotRepository(String persistenceUnitName) {
        super(persistenceUnitName, "idLot");
    }
    
}
