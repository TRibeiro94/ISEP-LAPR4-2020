package eapli.base.productionordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productionordermanagement.domain.ProductionOrder;
import eapli.base.productionordermanagement.repositories.ProductionOrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Pedro Borda de Água
 */
public class ProductionOrderController {

    private final ProductionOrderRepository repository = PersistenceContext.repositories().productionOrderRepository();

    public ProductionOrder prodOrder(String idOrder, LocalDate emissionMoment, LocalDate expectedExecDate, String manufacturingCode, int quantity, String measureUnit, String requestCodes) {
        final ProductionOrder newProdOrder = new ProductionOrder(idOrder, emissionMoment, expectedExecDate, manufacturingCode, quantity, measureUnit, requestCodes);
        return this.repository.save(newProdOrder);
    }

    public Iterable<ProductionOrder> getProductionOrder() {
        return this.repository.findAll();
    }
    
    public HashSet<ProductionOrder> checkProdOrdersByRequest(String order){
        HashSet<ProductionOrder> pos = new HashSet<>();
        
        for (ProductionOrder n : repository.findAll()) {
            int finish = (n.reqCodes().length() - 1);
            String aux2 = n.reqCodes().substring(1, finish);
            String [] requests = aux2.toLowerCase().split(",");
            
            for (String request : requests) {
                if(request.equals(order.toLowerCase())){
                    pos.add(n);
                }
            }
        }
        return pos;
    }
    
    public ArrayList<ProductionOrder> checkProdOrdersByState(boolean state){
        ArrayList<ProductionOrder> pos = new ArrayList<>();
        
        for (ProductionOrder productionOrder : repository.productionOrderByState(state)) {
            pos.add(productionOrder);
        }
        return pos;
    }
}
