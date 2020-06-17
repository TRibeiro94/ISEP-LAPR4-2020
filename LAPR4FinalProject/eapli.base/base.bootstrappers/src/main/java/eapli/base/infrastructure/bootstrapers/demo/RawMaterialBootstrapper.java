package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.rawmaterialmanagement.application.RegisterRawMaterialController;
import eapli.base.rawmaterialmanagement.domain.RawMaterialCategory;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialCategoryRepository;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RawMaterialBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(RawMaterialBootstrapper.class);
    private final RawMaterialCategoryRepository rawMatRepo = PersistenceContext.repositories().rawMaterialCategories();

    private RawMaterialCategory getCategory(String code) {
        return this.rawMatRepo.ofIdentity(code).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean execute() {
        final RawMaterialCategory cat1 = getCategory("AB1");
        final RawMaterialCategory cat2 = getCategory("AB2");
        final RawMaterialCategory cat3 = getCategory("AB3");

        register("AB245", "Tech1", "CAT1", cat1);
        register("AB500", "Tech2", "CAT2", cat2);
        register("AB100", "Tech3", "CAT3", cat3);
        return true;
    }

    private void register(String code, String technicalSheet, String description, RawMaterialCategory category) {
        final RegisterRawMaterialController controller = new RegisterRawMaterialController();
        try {
            controller.registerRawMaterial(code, technicalSheet, description, category);
        } catch (Exception e) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)");
            LOGGER.trace("Assuming existing record", e);
        }
    }
}
