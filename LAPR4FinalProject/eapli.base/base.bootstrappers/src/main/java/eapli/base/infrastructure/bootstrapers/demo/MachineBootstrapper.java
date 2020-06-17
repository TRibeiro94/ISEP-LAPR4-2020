/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.machinemanagement.application.RegisterMachineController;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.base.productionlinemanagement.repositories.ProductionLineRepository;
import eapli.framework.actions.Action;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Bernardo Carvalho
 */
public class MachineBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(DepositBootstrapper.class);
    private final ProductionLineRepository productionLineRepo = PersistenceContext.repositories().productionLineRepository();

    private ProductionLine getProductionLine(String code) {
        return this.productionLineRepo.ofIdentity(code).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean execute() {
        final ProductionLine pl1 = getProductionLine("PRODUCTION_LINE_1");
        final ProductionLine pl2 = getProductionLine("PRODUCTION_LINE_2");
        final ProductionLine pl3 = getProductionLine("PRODUCTION_LINE_3");

        register(pl1, "1", "612121", "123", "Machine1", "Brand1", "Model1", new Date(), 1);
        register(pl1, "2", "13212311111", "100", "Machine2", "Brand2", "Model2", new Date(), 2);
        register(pl1, "3", "23424123", "101", "Machine3", "Brand3", "Model3", new Date(), 3);
        register(pl1, "4", "234549373", "105", "Machine4", "Brand4", "Model4", new Date(), 4);
        register(pl2, "5", "29283637383", "108", "Machine5", "Brand5", "Model5", new Date(), 1);
        register(pl3, "6", "220292929", "109", "Machine6", "Brand6", "Model6", new Date(), 2);

        return true;
    }


    private void register(ProductionLine prodLine, String machineCode, String serialNum, String protocol, String desc, String brand, String model, Date installDate, Integer position) {

        final RegisterMachineController controller = new RegisterMachineController();

        try {
            controller.registerMachine(prodLine, machineCode, serialNum, protocol, desc, brand, model, installDate, position);
        } catch (Exception exc) {
            System.out.println("------------------------------------------------------------------------------");
            exc.printStackTrace();
            LOGGER.warn("Assuming {} already exists (activate trace log for details)");
            LOGGER.trace("Assuming existing record", exc);
        }
    }

}