package eapli.base.persistence.impl.inmemory;

import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryRawMaterialsRepository extends InMemoryDomainRepository<String, RawMaterial> implements RawMaterialRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public RawMaterial rawMaterialById(String rawMaterialCode) {
        return null;
    }
}
