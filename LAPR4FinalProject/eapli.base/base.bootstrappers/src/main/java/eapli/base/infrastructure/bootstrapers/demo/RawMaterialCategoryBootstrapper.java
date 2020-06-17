package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.rawmaterialmanagement.application.RegisterRawMaterialCategoryController;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RawMaterialCategoryBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(RawMaterialBootstrapper.class);

    @Override
    public boolean execute() {
        register("AB1", "Cat1");
        register("AB2", "Cat2");
        register("AB3", "Cat3");
        return true;
    }

    private void register(String code, String description) {
        final RegisterRawMaterialCategoryController controller = new RegisterRawMaterialCategoryController();
        try {
            controller.registerRawMaterialCategory(code, description);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warn("Assuming {} already exists (activate trace log for details)");
            LOGGER.trace("Assuming existing record", e);
        }
    }
}
