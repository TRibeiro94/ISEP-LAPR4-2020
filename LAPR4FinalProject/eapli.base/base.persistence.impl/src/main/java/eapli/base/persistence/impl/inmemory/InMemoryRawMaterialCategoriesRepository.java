package eapli.base.persistence.impl.inmemory;

import eapli.base.rawmaterialmanagement.domain.RawMaterialCategory;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialCategoryRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryRawMaterialCategoriesRepository extends InMemoryDomainRepository<String, RawMaterialCategory> implements RawMaterialCategoryRepository {

    static {
        InMemoryInitializer.init();
    }

}
