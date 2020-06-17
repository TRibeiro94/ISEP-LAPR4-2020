package eapli.base.persistence.impl.jpa;

import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.base.productionordermanagement.repositories.ProductionOrderRepository;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;

public class JpaProductionOrderRepository extends BasepaRepositoryBase<ProductionOrder, String, String> implements ProductionOrderRepository {

    JpaProductionOrderRepository(String persistenceUnitName) {
        super(persistenceUnitName, "idOrder");
    }

    @Override
    public Iterable<ProductionOrder> allProductionOrder() {
        return null;
    }

    @Override
    public Iterable<ProductionOrder> productionOrdersBetweenDates(LocalDate leftDateProdOrder, LocalDate rightDateProdOrder) {
        final TypedQuery<ProductionOrder> query = createQuery("SELECT m FROM ProductionOrder m WHERE m.emissionDate BETWEEN :dataInicio AND :dataFim", ProductionOrder.class);
        query.setParameter("dataInicio", leftDateProdOrder);
        query.setParameter("dataFim", rightDateProdOrder);
        try{
            return query.getResultList();
        } catch(NoResultException e){
            return new ArrayList();
        }
    }
    
    @Override
    public ProductionOrder productionOrderById(String productionOrderCode){
        final TypedQuery<ProductionOrder> query = createQuery("SELECT m FROM ProductionOrder m WHERE m.idOrder = :prodOrderCode", ProductionOrder.class);
        query.setParameter("prodOrderCode", productionOrderCode);
        try{
            return query.getSingleResult();
        } catch(NoResultException e){
            return null;
        }
    }
    
    @Override
    public List<ProductionOrder> productionOrderByState(boolean state) {
        final TypedQuery<ProductionOrder> query = createQuery("SELECT m FROM ProductionOrder m WHERE m.executing = :state", ProductionOrder.class);
        query.setParameter("state", state);
        try{
            return query.getResultList();
        } catch(NoResultException e){
            return new ArrayList<>();
        }
    }
    
    /*public List<ProductionOrder> hasOrder() {
        TypedQuery<ProductionOrder> query = createQuery("SELECT po FROM PRODUCTIONORDER po WHERE po.REQUESTCODES='EC2020'", ProductionOrder.class);
        return query.getResultList();
    }*/
}
