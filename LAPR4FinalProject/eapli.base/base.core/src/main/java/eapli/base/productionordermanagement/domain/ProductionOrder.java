package eapli.base.productionordermanagement.domain;

import eapli.base.productmanagement.domain.Quantity;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.ElementCollection;

/**
 *
 * @author Pedro Borda de Água
 */
@Entity
public class ProductionOrder implements AggregateRoot<String> {

    @Id
    private String idOrder;
    private LocalDate emissionDate;
    private LocalDate expectedDate;
    private String productCode;
    private Quantity quantity;
    private String requestCodes;
    
    private boolean executing;
    private Date executionBegin;
    private Date executionEnd;
    Integer grossTime;
    Integer efectiveTime;
    @ElementCollection
    private Set<DetailedMachineTimes> detailedMachineTimes = new HashSet<>();
    private Integer rawMaterialActualConsumption;
    @ElementCollection
    private Set<Lot> lots = new HashSet<>();
    @ElementCollection
    private Set<String> stockMovements = new HashSet<>();
    

    protected ProductionOrder() {
        //for ORM
    }

    public ProductionOrder(String idOrder, LocalDate emissionDate, LocalDate expectedDate, String productCode, int quantity, String measureUnit, String requestCodes) {
        this.idOrder = idOrder;
        this.emissionDate = emissionDate;
        this.expectedDate = expectedDate;
        this.productCode = productCode;
        this.quantity = new Quantity(quantity, measureUnit);
        this.requestCodes = requestCodes;
        this.executing = false;
        this.executionBegin = null;
        this.executionEnd = null;
        this.grossTime = null;
        this.efectiveTime = null;
        this.rawMaterialActualConsumption = null;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ProductionOrder)) {
            return false;
        }

        final ProductionOrder that = (ProductionOrder) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public String identity() {
        return this.idOrder;
    }

    public LocalDate emissionDate() {
        return this.emissionDate;
    }

    public LocalDate expectedDate() {
        return this.expectedDate;
    }

    public String prodCode() {
        return this.productCode;
    }

    public Quantity quantity() {
        return this.quantity;
    }

    public String reqCodes() {
        return this.requestCodes;
    }
    
    public Date beginDate(){
        return this.executionBegin;
    }
    
    public Date endDate(){
        return this.executionEnd;
    }
    
    public Set<DetailedMachineTimes> detailedMachineTimes(){
        return this.detailedMachineTimes;
    }
    
    public void addLot(Lot lot){
        this.lots.add(lot);
    }
    
    public void addStockMovement(String movement){
        this.stockMovements.add(movement);
    }
    
    public void updateExecutionBeginDate(Date dateBegin){
        this.executionBegin = dateBegin;
    }
    
    public void updateExecutionEndDate(Date dateEnd){
        this.executionEnd = dateEnd;
    }

    @Override
    public String toString() {
        return String.format("Production Order: %s, Emission Date: %s, Expected Date: %s, Product ID: %s, (Quantity: %d, MeasureUnit: %s)\n", this.identity(), this.emissionDate().toString(), this.expectedDate().toString(), this.prodCode(), this.quantity().checkQuantity(), this.quantity().measureUnit());
    }
}
