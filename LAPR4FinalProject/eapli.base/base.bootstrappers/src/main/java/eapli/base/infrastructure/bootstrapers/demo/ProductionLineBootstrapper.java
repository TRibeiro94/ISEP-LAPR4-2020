/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.productionlinemanagement.application.SpecifyProductionLineController;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Tiago Ribeiro
 */

public class ProductionLineBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(ProductionLineBootstrapper.class);

    @Override
    public boolean execute() {

        register("PRODUCTION_LINE_1");
        register("PRODUCTION_LINE_2");
        register("PRODUCTION_LINE_3");

        return true;
    }

    private void register(String productionLineCode) {

        final SpecifyProductionLineController controller = new SpecifyProductionLineController();

        try {
            controller.registerProductionLine(productionLineCode);
        } catch (Exception e) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)");
            LOGGER.trace("Assuming existing record", e);
        }
    }

}
