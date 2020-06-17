package eapli.base.rawmaterialmanagement.repositories;

import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.framework.domain.repositories.DomainRepository;

public interface RawMaterialRepository extends DomainRepository<String, RawMaterial> {
    
    public RawMaterial rawMaterialById(String rawMaterialCode);
}
