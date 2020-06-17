package eapli.base.persistence.impl.jpa;

import eapli.base.rawmaterialmanagement.domain.RawMaterialCategory;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialCategoryRepository;

public class JpaRawMaterialCategoriesRepository extends BasepaRepositoryBase<RawMaterialCategory, String, String> implements RawMaterialCategoryRepository {

    JpaRawMaterialCategoriesRepository(String puname) {
        super(puname, "id");
    }
}
