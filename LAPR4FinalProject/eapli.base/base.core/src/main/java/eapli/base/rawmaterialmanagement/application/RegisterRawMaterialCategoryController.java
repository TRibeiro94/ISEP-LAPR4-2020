package eapli.base.rawmaterialmanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.rawmaterialmanagement.domain.RawMaterialCategory;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialCategoryRepository;

/**
 * This controller registers a new Raw Material Category in the database
 */
public class RegisterRawMaterialCategoryController {

    private final RawMaterialCategoryRepository repository = PersistenceContext.repositories().rawMaterialCategories();

    public boolean checkAvailableCode(String code) {
        boolean codeAvailability = RawMaterialCategory.verifyCode(code, this.getRawMaterialCategory());

        if (codeAvailability == false) {
            return false;
        }
        return true;
    }

    /**
     * This method creates a new RawMaterialCategory and saves it in the database
     *
     * @param code        The code of the RawMaterialCategory
     * @param description The description of the RawMaterialCategory
     * @return the instance saved in the database
     */
    public RawMaterialCategory registerRawMaterialCategory(String code, String description) {
        final RawMaterialCategory newRawMaterialCategory = new RawMaterialCategory(code, description);
        return this.repository.save(newRawMaterialCategory);
    }

    public Iterable<RawMaterialCategory> getRawMaterialCategory() {
        return this.repository.findAll();
    }
}
