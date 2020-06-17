package eapli.base.persistence.impl.jpa;

import eapli.base.productionlinemanagement.domain.ProductionLine;
import eapli.base.productionlinemanagement.repositories.ProductionLineRepository;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class JpaProductionLineRepository extends BasepaRepositoryBase<ProductionLine, String, String> implements ProductionLineRepository {

    JpaProductionLineRepository(String identityFieldName) {
        super(identityFieldName, "code");
    }
    
    @Override
    public ProductionLine productionLineByID(String productionLineCode) throws NoResultException{
        final TypedQuery<ProductionLine> query = createQuery("SELECT m FROM ProductionLine m WHERE m.code = :plCode", ProductionLine.class);
        query.setParameter("plCode", productionLineCode);
        try{
            return query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
    
    @Override
    public ProductionLine productionLineByStatusFalse(String productionLineCode) throws NoResultException{
        boolean status = false;
        final TypedQuery<ProductionLine> query = createQuery("SELECT m FROM ProductionLine m WHERE m.code = :plCode AND messageProcessingStatus = :plstatus", ProductionLine.class);
        query.setParameter("plCode", productionLineCode);
        query.setParameter("plstatus", status);
        try{
            return query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }

}
