package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialRepository;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(ProductBootstrapper.class);
    private final RawMaterialRepository rawMatRepo = PersistenceContext.repositories().rawMaterials();

    private RawMaterial getRawMaterial(String code) {
        return this.rawMatRepo.ofIdentity(code).orElseThrow(IllegalStateException::new);
    }

    @Override
    public boolean execute() {
        RawMaterial r1 = getRawMaterial("AB245");
        RawMaterial r2 = getRawMaterial("AB500");
        registerWithBillOfMaterials("product10", "Prod1", "PR1", "Produto de venda 1", "PR-0011", r1, 4, "UN", "KG");
        registerWithBillOfMaterials("product20", "Prod2", "PR2", "Produto de venda 2", "PR-4609", r2, 2, "UN", "CL");
        registerWithoutBillOfMaterials("product30", "Prod3", "PR3", "Produto de venda 3", "PD-0011", "KG");
        registerWithoutBillOfMaterials("product40", "Prod4", "PR4", "Produto de venda 4", "PD-9999", "KG");
        return true;
    }

    private void registerWithBillOfMaterials(String code, String commercialCode, String briefDescription, String fullDescription, String cat, RawMaterial rawMaterial, Integer quantity, String measureUnitProd, String measureUnitRaw) {
        final RegisterProductController controller = new RegisterProductController();
        try {
            controller.registerProduct(code, commercialCode, briefDescription, fullDescription, cat, rawMaterial, quantity, measureUnitProd, measureUnitRaw);
        } catch (Exception e) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)");
            LOGGER.trace("Assuming existing record", e);
        }
    }

    private void registerWithoutBillOfMaterials(String code, String commercialCode, String briefDescription, String fullDescription, String category, String measureUnit) {
        final RegisterProductController controller = new RegisterProductController();
        try {
            controller.registerProductWithoutBOM(code, commercialCode, briefDescription, fullDescription, category, measureUnit);
        } catch (Exception e) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)");
            LOGGER.trace("Assuming existing record", e);
        }
    }
}
