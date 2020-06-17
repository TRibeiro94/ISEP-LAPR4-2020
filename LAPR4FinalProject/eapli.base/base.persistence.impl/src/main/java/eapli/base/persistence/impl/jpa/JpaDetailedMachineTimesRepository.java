/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.productionordermanagement.domain.DetailedMachineTimes;
import eapli.base.productionordermanagement.repositories.DetailedMachineTimesRepository;

/**
 *
 * @author Bernardo Carvalho
 */
public class JpaDetailedMachineTimesRepository extends BasepaRepositoryBase<DetailedMachineTimes, String, String> implements DetailedMachineTimesRepository{
    
    public JpaDetailedMachineTimesRepository(String persistenceUnitName) {
        super(persistenceUnitName, "detailedMachineTimesCode");
    }
    
}
