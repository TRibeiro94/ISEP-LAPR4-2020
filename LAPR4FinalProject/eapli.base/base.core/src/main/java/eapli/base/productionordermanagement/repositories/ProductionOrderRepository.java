package eapli.base.productionordermanagement.repositories;

import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.framework.domain.repositories.DomainRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public interface ProductionOrderRepository extends DomainRepository<String, ProductionOrder> {

    Iterable<ProductionOrder> allProductionOrder();

    Iterable<ProductionOrder> productionOrdersBetweenDates(LocalDate leftDateProdOrder, LocalDate rightDateProdOrder);
    
    ProductionOrder productionOrderById(String productionOrderCode);
    
    List<ProductionOrder> productionOrderByState(boolean state);
}
