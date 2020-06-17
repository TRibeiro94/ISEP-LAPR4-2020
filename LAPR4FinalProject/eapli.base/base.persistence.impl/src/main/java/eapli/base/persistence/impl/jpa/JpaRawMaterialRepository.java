package eapli.base.persistence.impl.jpa;

import eapli.base.rawmaterialmanagement.domain.RawMaterial;
import eapli.base.rawmaterialmanagement.repositories.RawMaterialRepository;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

class JpaRawMaterialRepository extends BasepaRepositoryBase<RawMaterial, String, String> implements RawMaterialRepository {

    public JpaRawMaterialRepository(String puname) {
        super(puname, "code");
    }
    
    @Override
    public RawMaterial rawMaterialById(String rawMaterialCode){
        final TypedQuery<RawMaterial> query = createQuery("SELECT m FROM RawMaterial m WHERE m.code = :rmCode", RawMaterial.class);
        query.setParameter("rmCode", rawMaterialCode);
        try{
            return query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
}
