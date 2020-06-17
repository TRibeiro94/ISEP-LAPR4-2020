/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.productionordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionordermanagement.domain.Lot;
import eapli.base.productionordermanagement.repositories.LotRepository;

/**
 *
 * @author Bernardo Carvalho
 */
public class LotController {
    
    private final LotRepository lotRepo = PersistenceContext.repositories().lotRepository();
    
    public Lot registerLot(String idLot, String idProductionOrder, Integer quantity){
        final Lot lot = new Lot(idLot, idProductionOrder, quantity);
        return this.lotRepo.save(lot);
    }
    
}
