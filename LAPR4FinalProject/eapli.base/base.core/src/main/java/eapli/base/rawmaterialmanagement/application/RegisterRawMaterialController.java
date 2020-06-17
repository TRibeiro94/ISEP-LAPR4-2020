package eapli.base.rawmaterialmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionlinemanagement.application.ProductionLineService;
import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.domain.RawMaterialCategory;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialRepository;

/**
 * This class registers a Raw Material in the database
 */
public class RegisterRawMaterialController {

    //adicionar autenticacao
    private final RawMaterialRepository repository = PersistenceContext.repositories().rawMaterials();

    /**
     * This method registers a new Raw Material in the database
     *
     * @param code           The code of the Raw Material
     * @param technicalSheet The technicalSheet of the Raw Material
     * @param description    The description of the Raw Material
     * @param category       The category of the Raw Material
     * @return the instance saved in the database
     */
    public RawMaterial registerRawMaterial(String code, String technicalSheet, String description, RawMaterialCategory category) {
        final RawMaterial newRawMaterial = new RawMaterial(code, technicalSheet, description, category);
        return this.repository.save(newRawMaterial);
    }

    public Iterable<RawMaterial> getRawMaterial() {
        return this.repository.findAll();
    }
}
